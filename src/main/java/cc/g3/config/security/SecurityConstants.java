package cc.g3.config.security;

/**
 * 
 * 
 * Constants used to create JSON  web tokens
 *
 */
public class SecurityConstants {
	
	/**
	 * Header key for every request JSON token
	 */
	public static final String HEADER_STRING = "Authorization";
	
	/**
	 * Prefix for every JSON token
	 */
	public static final String TOKEN_PREFIX = "ccmid ";
	
	/**
	 * Secret to encode and decode the JSON token
	 */
	public static final String SECRET = "ccmid";
	
	/**
	 * Sets the expiry time for JSON web tokens
	 */
	public static final long EXPIRATION_TIME = 360000L; //5mins
	
}
