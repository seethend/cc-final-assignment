/**
 * 
 */
package cc.g3.users.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cc.g3.users.models.User;

/**
 * 
 * User data access layer
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {

	/**
	 * 
	 * Find User entity by Username
	 *
	 * @param username
	 * @return Optional<User>
	 */
	public Optional<User> findByUsername(String username);
	
}
