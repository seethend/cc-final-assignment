package cc.g3.config.security;

import static cc.g3.config.security.SecurityConstants.*;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import cc.g3.users.services.UserService;
import io.jsonwebtoken.Jwts;

/**
 * 
 * Once authentication is completed successfully, every request from same user will have JSON web token in request header
 *
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private final UserService customUserDetailService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserService customUserDetailService) {
        super(authenticationManager);
        this.customUserDetailService = customUserDetailService;
    }

    /**
     * 
     * Filter for every incoming request
     * 
     */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String header = request.getHeader(HEADER_STRING);

		if(header == null || !header.startsWith(TOKEN_PREFIX)) {

			chain.doFilter(request, response);
			return;
		}
		
		UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		chain.doFilter(request, response);
	}

	/**
	 * 
	 * Parses the Auth token from header and authenticates the user
	 *
	 * @param request
	 * @return UsernamePasswordAuthenticationToken
	 */
	private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if(token == null) return null;
		
		String username = Jwts
							.parser()
							.setSigningKey(SECRET)
							.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
							.getBody()
							.getSubject();
		UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
		return username != null ?
				new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()) : null;
	}


}
