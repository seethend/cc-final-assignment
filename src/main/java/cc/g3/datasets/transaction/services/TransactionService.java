package cc.g3.datasets.transaction.services;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.g3.datasets.household.models.HouseHold;
import cc.g3.datasets.household.services.HouseHoldService;
import cc.g3.datasets.product.models.Product;
import cc.g3.datasets.product.services.ProductService;
import cc.g3.datasets.services.UtilityService;
import cc.g3.datasets.transaction.models.Transaction;
import cc.g3.datasets.transaction.models.dto.TransactionMapper;
import cc.g3.datasets.transaction.models.dto.TransactionModel;
import cc.g3.datasets.transaction.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Transaction service layer
 *
 */
@Service
@Slf4j
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private HouseHoldService householdService;

	@Autowired
	private ProductService productService;
	
	DateFormat df = new SimpleDateFormat("dd-MMM-yy");

	/**
	 * 
	 * Saved transaction entity
	 * 
	 * @param transaction
	 * @return Transaction
	 */
	public Transaction saveTransaction(Transaction transaction) {
		
		Transaction savedTransaction = null;
		
		try {
			transaction = transactionRepository.save(transaction);
		} catch(Exception e) {
			log.error("Error while saving the transaction entity ::: " + e.getMessage());
			log.error("Error stack ::: " + Arrays.toString(e.getStackTrace()));
		}
		
		return savedTransaction;
	}
	
	/**
	 * 
	 * Saves list of transaction entities
	 *
	 * @param transactions
	 * @return List<Transaction>
	 */
	public List<Transaction> saveAllTransactions(List<Transaction> transactions) {
		
		List<Transaction> savedTransactions = null;
		
		try {
			savedTransactions = transactionRepository.saveAll(transactions);
		} catch(Exception e) {
			log.error("Error while saving the transaction entity ::: " + e.getMessage());
			log.error("Error stack ::: " + Arrays.toString(e.getStackTrace()));
		}
		
		return savedTransactions;
	}
	
	/**
	 * 
	 * Converts transaction DTO to entity and saves to database
	 *
	 * @param transactionModel
	 * @return TransactionModel
	 */
	public TransactionModel saveTransactionModel(TransactionModel transactionModel) {
		
		TransactionModel savedTransactionModel = null;
		
		try {
			
			Transaction transaction = TransactionMapper.toEntity(transactionModel);
					
			transaction = saveTransaction(transaction);
			
			if(transaction != null)
				savedTransactionModel = TransactionMapper.toModel(transaction);
			
		} catch(Exception e) {
			log.error("Error while saving the transaction model ::: " + e.getMessage());
			log.error("Error stack ::: " + Arrays.toString(e.getStackTrace()));
		}
		
		return savedTransactionModel;
	}

	/**
	 * 
	 * Splits the List of CSV records and creates the list of transaction entities
	 *
	 * @param fileRows
	 * @return List<String>
	 */
	public List<String> loadFileRows(List<String> fileRows) {
		List<String> erroredTransactionRows = new ArrayList<>();
		List<Transaction> transactionEntities = new ArrayList<>();
		 
		for(int i = 1; i < fileRows.size(); i++) {
			String record = fileRows.get(i);
			
			if(record != null && !record.trim().isEmpty()) {
				String[] columns = record.split(",");
				
				Transaction transaction = new Transaction();
				
				if(UtilityService.checkAndFetchIntegerValue(columns, 0) && UtilityService.checkAndFetchIntegerValue(columns, 1) && UtilityService.checkAndFetchValue(columns, 2) && 
						UtilityService.checkAndFetchIntegerValue(columns, 3) && UtilityService.checkAndFetchDoubleValue(columns, 4) && UtilityService.checkAndFetchIntegerValue(columns, 5) && 
						UtilityService.checkAndFetchValue(columns, 6) && UtilityService.checkAndFetchIntegerValue(columns, 7) && UtilityService.checkAndFetchIntegerValue(columns, 8)) {
					
					transaction.setBasketNum(Integer.parseInt(columns[0].trim()));
					
					HouseHold houseHold = householdService.getHouseHoldById(Integer.parseInt(columns[1].trim()));
					
					if(houseHold != null)
						transaction.setHouseHold(houseHold);
					
					java.util.Date date = null;
					
					String dateStr = columns[2].trim();
					
					try {
						date = df.parse(dateStr);
					} catch (ParseException e) {
						log.error("Error while converting string to date format ::: " + e.getMessage());
					}
					
					if(date != null)
						transaction.setPurchaseDate(new Date(date.getTime()));
					
					Product product = productService.getProductById(Integer.parseInt(columns[3].trim()));
					
					if(product != null)
						transaction.setProduct(product);
					
					transaction.setSpend(Double.parseDouble(columns[4].trim()));
					transaction.setUnits(Integer.parseInt(columns[5].trim()));
					transaction.setStoreRegion(columns[6].trim());
					transaction.setWeekNum(Integer.parseInt(columns[7].trim()));
					transaction.setYear(Integer.parseInt(columns[8].trim()));
					
					transactionEntities.add(transaction);
				} else {
					erroredTransactionRows.add(record);
				}
			}
		}
		
		saveAllTransactions(transactionEntities);
		
		return erroredTransactionRows;
	}

	/**
	 * 
	 * Finds all the transaction entities based on household number
	 *
	 * @param prodNum
	 * @return Product
	 */
	public List<TransactionModel> fetchTransactionsOnHsHdId(int hshdId) {

		List<TransactionModel> transactionModels = new ArrayList<>();
		
		List<Transaction> transactions = transactionRepository.fetchByHouseHoldId(hshdId);
		
		if(transactions != null) {
			for(Transaction transaction: transactions) {
				transactionModels.add(TransactionMapper.toModel(transaction));
			}
		}
		
		return transactionModels;
	}
	
}
