package cc.g3.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.g3.users.models.dto.UserModel;
import cc.g3.users.services.UserService;

/**
 * 
 * Rest controller to perfrom operations on User entity
 *
 */
@RestController
@RequestMapping("/v1/user/")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * Rest Mapping to register user
	 *
	 * @param userModel
	 * @return
	 */
	@PostMapping("/signup")
	public ResponseEntity<String> saveUser(@RequestBody UserModel userModel) {
		
		UserModel savedUserModel = userService.saveUserModel(userModel);
		String status = "User saved successfully !!!";
		HttpStatus httpStatus = HttpStatus.OK;
		
		if(savedUserModel == null) {
			status = "Failed to save user !!!";
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<String>(status, httpStatus);
	}
	
}
