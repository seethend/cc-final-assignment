package cc.g3.datasets.product.models.dto;

import java.util.Arrays;

import cc.g3.datasets.product.models.Product;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Product entity to data transfer object mapper
 *
 */
@Slf4j
public class ProductMapper {

	/**
	 * 
	 * DTO to Entity mapper
	 *
	 * @param productModel
	 * @return Product
	 */
	public static Product toEntity(ProductModel productModel) {
		
		Product product = new Product();
		
		try {
			product.setId(productModel.getId());
			product.setProductNum(productModel.getProductNum());
			product.setDepartment(productModel.getDepartment());
			product.setCommodity(productModel.getCommodity());
			product.setBrandType(productModel.getBrandType());
			product.setNaturalOrganicFlag(productModel.isNaturalOrganicFlag());
			
		} catch(Exception e) {
			log.error("Error occured while transforming Product model to entity ::: " + e.getMessage());
			log.error(Arrays.toString(e.getStackTrace()));
			return null;
		}
		
		return product;
	}
	
	/**
	 * 
	 * Entity to DTO mapper
	 *
	 * @param product
	 * @return ProductModel
	 */
	public static ProductModel toModel(Product product) {
		
		ProductModel productModel = new ProductModel();
		
		try {

			productModel.setId(productModel.getId());
			productModel.setProductNum(product.getProductNum());
			productModel.setDepartment(product.getDepartment());
			productModel.setCommodity(product.getCommodity());
			productModel.setBrandType(product.getBrandType());
			productModel.setNaturalOrganicFlag(product.isNaturalOrganicFlag());
			
//			if(product.getTransactions() != null)
//				productModel.setTransactionIds(
//					product.getTransactions()
//							.stream()
//							.map(p -> p.getId()).collect(Collectors.toSet())
//				);
			
		} catch(Exception e) {
			log.error("Error occured while transforming Product entity to model ::: " + e.getMessage());
			log.error(Arrays.toString(e.getStackTrace()));
			return null;
		}
		
		return productModel;
	}
}
