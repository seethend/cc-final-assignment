package cc.g3.datasets.transaction.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cc.g3.datasets.transaction.models.Transaction;

/**
 * 
 * Transaction Data access layer
 *
 */
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	/**
	 * 
	 * Query returns all the transaction data based on household number
	 *
	 * @param hshdId
	 * @return
	 */
	@Query("SELECT t FROM Transaction t WHERE t.houseHold.hshdNum = :hshdId ORDER BY t.houseHold.hshdNum, t.basketNum, t.purchaseDate, t.product.productNum, t.product.department, t.product.commodity")
	List<Transaction> fetchByHouseHoldId(@Param("hshdId") int hshdId);

}
