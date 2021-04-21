package cc.g3.users.services;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cc.g3.users.models.CustomUserDetails;
import cc.g3.users.models.User;
import cc.g3.users.models.dto.UserMapper;
import cc.g3.users.models.dto.UserModel;
import cc.g3.users.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * User service layer
 *
 */
@Service
@Slf4j
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 
	 * Load by User by username
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Optional<User> optionalUsers = userRepository.findByUsername(username);
		optionalUsers.orElseThrow(()-> new UsernameNotFoundException("Username Not Found"));
		return optionalUsers.map(CustomUserDetails::new).get();
	}
	
	public User getUserByUsername(String username) {
		User user = null;
		Optional<User> optionalUsers = userRepository.findByUsername(username);
		if(optionalUsers.isPresent()) {
			user = optionalUsers.get();
		}
		return user;
	}
	
	/**
	 * 
	 * Converts user model to entity and saves it
	 *
	 * @param userModel
	 * @return UserModel
	 */
	public UserModel saveUserModel(UserModel userModel) {
		
		User user = UserMapper.toEntity(userModel);
		
		if(user != null)
			user = savedUser(user);
		
		if(user != null)
			userModel = UserMapper.toModel(user);
		
		return userModel;		
	}

	/**
	 * 
	 * Saves user entity to database
	 *
	 * @param user
	 * @return User
	 */
	private User savedUser(User user) {
		
		try {
			
			return userRepository.save(user);
			
		} catch (Exception e) {
			log.error("Error while saving the user entity ::: " +  e.getMessage());
			log.error("Error stack ::: " + Arrays.toString(e.getStackTrace()));
		}
		
		return null;
	}
}
