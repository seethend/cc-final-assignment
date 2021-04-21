package cc.g3.datasets.household.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cc.g3.datasets.household.models.HouseHold;

/**
 * 
 * Household data access layers 
 *
 */
public interface HouseHoldRepository extends JpaRepository<HouseHold, Integer> {

	/**
	 * 
	 * Find house hold entity based on household number
	 *
	 * @param hshdId
	 * @return Optional<HouseHold>
	 */
	Optional<HouseHold> findByHshdNum(int hshdId);

}
