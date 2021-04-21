package cc.g3.users.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * User data transfer object
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

	@JsonProperty
	private int id;

	@JsonProperty
	private String username;

	@JsonProperty
	private String password;

	@JsonProperty
	private String fullName;

	@JsonProperty
	private String email;
	
}
