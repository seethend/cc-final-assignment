package cc.g3.datasets.product.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.g3.datasets.product.models.Product;
import cc.g3.datasets.product.models.dto.ProductMapper;
import cc.g3.datasets.product.models.dto.ProductModel;
import cc.g3.datasets.product.repositories.ProductRepository;
import cc.g3.datasets.services.UtilityService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Product service layer
 *
 */
@Service
@Slf4j
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	/**
	 * 
	 * Saved product entity
	 * 
	 * @param product
	 * @return Product
	 */
	public Product saveProduct(Product product) {
		
		Product savedProduct = null;
		
		try {
			product = productRepository.save(product);
		} catch(Exception e) {
			log.error("Error while saving the product entity ::: " + e.getMessage());
			log.error("Error stack ::: " + Arrays.toString(e.getStackTrace()));
		}
		
		return savedProduct;
	}
	
	/**
	 * 
	 * Saves list of product entities
	 *
	 * @param products
	 * @return List<Product>
	 */
	public List<Product> saveAllProducts(List<Product> products) {
		
		List<Product> savedProducts = null;
		
		try {
			savedProducts = productRepository.saveAll(products);
		} catch(Exception e) {
			log.error("Error while saving the household entity ::: " + e.getMessage());
			log.error("Error stack ::: " + Arrays.toString(e.getStackTrace()));
		}
		
		return savedProducts;
	}
	
	/**
	 * 
	 * Converts product DTO to entity and saves to database
	 *
	 * @param productModel
	 * @return ProductModel
	 */
	public ProductModel saveProductModel(ProductModel productModel) {
		
		ProductModel savedProductModel = null;
		
		try {
			
			Product product = ProductMapper.toEntity(productModel);
					
			product = saveProduct(product);
			
			if(product != null)
				savedProductModel = ProductMapper.toModel(product);
			
		} catch(Exception e) {
			log.error("Error while saving the product model ::: " + e.getMessage());
			log.error("Error stack ::: " + Arrays.toString(e.getStackTrace()));
		}
		
		return savedProductModel;
	}

	/**
	 * 
	 * Splits the List of CSV records and creates the list of product entities
	 *
	 * @param fileRows
	 * @return List<String>
	 */
	public List<String> loadFileRows(List<String> fileRows) {
		List<String> erroredProductRows = new ArrayList<>();
		List<Product> productEntities = new ArrayList<>();
		 
		for(int i = 1; i < fileRows.size(); i++) {
			String record = fileRows.get(i);
			
			if(record != null && !record.trim().isEmpty()) {
				String[] columns = record.split(",");
				
				Product product = new Product();
				
				if(UtilityService.checkAndFetchIntegerValue(columns, 0) && UtilityService.checkAndFetchValue(columns, 1) && 
						UtilityService.checkAndFetchValue(columns, 2) && UtilityService.checkAndFetchValue(columns, 3) && 
						UtilityService.checkAndFetchValue(columns, 4)) {
					
					product.setProductNum(Integer.parseInt(columns[0].trim()));
					product.setDepartment(columns[1].trim());
					product.setCommodity(columns[2].trim());
					product.setBrandType(columns[3].trim());
					product.setNaturalOrganicFlag(columns[4].trim().equals("Y") ? true : false);
					
					productEntities.add(product);
				} else {
					erroredProductRows.add(record);
				}
			}
		}
		
		saveAllProducts(productEntities);
		
		return erroredProductRows;
	}

	/**
	 * 
	 * Finds product entity based on product number
	 *
	 * @param prodNum
	 * @return Product
	 */
	public Product getProductById(int prodNum) {
		
		Optional<Product> optionalProduct = productRepository.findByProductNum(prodNum);
		
		if(optionalProduct.isPresent())
			return optionalProduct.get();
		
		log.error("Product hold entity doesn't exists for id " + prodNum);
		
		return null;		
	}
	
}
