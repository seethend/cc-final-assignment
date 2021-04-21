package cc.g3.datasets.product.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cc.g3.datasets.product.models.Product;

/**
 * 
 * Product entity data access layer
 *
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

	/**
	 * 
	 * Finds the product entity based on product number
	 *
	 * @param id
	 * @return Optional<Product>
	 */
	Optional<Product> findByProductNum(int id);

}
