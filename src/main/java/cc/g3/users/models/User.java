package cc.g3.users.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * User entity table
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
    @Column(name = "user_id")
	private int id;
	
	@Column(name = "username", nullable = false, length = 250, unique = true)
	private String username;
	
	@Column(name = "password", nullable = false, length = 250)
	private String password;
	
    @Column(name = "full_name", nullable = false, length = 250)
	private String fullName;
	
    @Column(name = "email", nullable = false, length = 250)
	private String email;

	public User(User user) {
		this.id = user.id;
		this.fullName = user.fullName;
		this.email = user.email;
		this.username = user.username;
		this.password = user.password;
	}
}
