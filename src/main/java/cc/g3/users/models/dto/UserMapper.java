package cc.g3.users.models.dto;

import java.util.Arrays;

import cc.g3.users.models.User;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * User DTO mapper
 *
 */
@Slf4j
public class UserMapper {

	/**
	 * 
	 * User DTO to entity mapper
	 *
	 * @param userModel
	 * @return User
	 */
	public static User toEntity(UserModel userModel) {
		
		User user = new User();
		
		try {
			user.setId(userModel.getId());
			user.setPassword(userModel.getPassword());
			user.setUsername(userModel.getUsername());
			user.setFullName(userModel.getFullName());
			user.setEmail(userModel.getEmail());
		} catch (Exception e) {
			log.error("Error occured while transforming user model to entity ::: " + e.getMessage());
			log.error(Arrays.toString(e.getStackTrace()));
			return null;
		}
		
		return user;		
	}
	
	/**
	 * 
	 * User entity to DTO mapper
	 *
	 * @param user
	 * @return UserModel
	 */
	public static UserModel toModel(User user) {
		
		UserModel userModel = new UserModel();
		
		try {
			userModel.setId(user.getId());
			userModel.setUsername(user.getUsername());
			userModel.setFullName(user.getFullName());
			userModel.setEmail(user.getEmail());
		} catch (Exception e) {
			log.error("Error occured while transforming user entity to model ::: " + e.getMessage());
			log.error(Arrays.toString(e.getStackTrace()));
			return null;
		}
		
		return userModel;
	}
	
}
