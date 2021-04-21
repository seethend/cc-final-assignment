package cc.g3.datasets.transaction.models.dto;

import java.util.Arrays;

import cc.g3.datasets.household.models.dto.HouseHoldMapper;
import cc.g3.datasets.product.models.dto.ProductMapper;
import cc.g3.datasets.transaction.models.Transaction;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Transaction entity to data transfer object mapper
 *
 */
@Slf4j
public class TransactionMapper {

	/**
	 * 
	 * DTO to Entity mapper
	 *
	 * @param transactionModel
	 * @return
	 */
	public static Transaction toEntity(TransactionModel transactionModel) {
		
		Transaction transaction = new Transaction();
		
		try {
			
			transaction.setId(transactionModel.getId());
			transaction.setBasketNum(transactionModel.getBasketNum());
			
			transaction.setPurchaseDate(transactionModel.getPurchaseDate());
			
			transaction.setSpend(transactionModel.getSpend());
			transaction.setUnits(transactionModel.getUnits());
			transaction.setStoreRegion(transactionModel.getStoreRegion());
			transaction.setWeekNum(transactionModel.getWeekNum());
			transaction.setYear(transactionModel.getYear());
			
		} catch(Exception e) {
			log.error("Error occured while transforming Transaction model to entity ::: " + e.getMessage());
			log.error(Arrays.toString(e.getStackTrace()));
			return null;
		}
		
		return transaction;
		
	}
	
	/**
	 * 
	 * Entity to DTO mapper
	 *
	 * @param transaction
	 * @return
	 */
	public static TransactionModel toModel(Transaction transaction) {
		
		TransactionModel transactionModel = new TransactionModel();
		
		try {
			
			transactionModel.setId(transaction.getId());
			transactionModel.setBasketNum(transaction.getBasketNum());
			
			if(transaction.getHouseHold() != null)
				transactionModel.setHouseHoldModel(HouseHoldMapper.toModel(transaction.getHouseHold()));
			
			transactionModel.setPurchaseDate(transaction.getPurchaseDate());

			if(transaction.getProduct() != null)
				transactionModel.setProductModel(ProductMapper.toModel(transaction.getProduct()));
			
			transactionModel.setSpend(transaction.getSpend());
			transactionModel.setUnits(transaction.getUnits());
			transactionModel.setStoreRegion(transaction.getStoreRegion());
			transactionModel.setWeekNum(transaction.getWeekNum());
			transactionModel.setYear(transaction.getYear());
			
		} catch(Exception e) {
			log.error("Error occured while transforming Transaction entity to model ::: " + e.getMessage());
			log.error(Arrays.toString(e.getStackTrace()));
			return null;
		}
		
		return transactionModel;
		
	}
	
}
