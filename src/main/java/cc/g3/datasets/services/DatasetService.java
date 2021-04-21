package cc.g3.datasets.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cc.g3.datasets.household.services.HouseHoldService;
import cc.g3.datasets.product.services.ProductService;
import cc.g3.datasets.transaction.models.dto.TransactionModel;
import cc.g3.datasets.transaction.services.TransactionService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Service handles loading of CSV files and retrieval of transaction entities based on household number
 *
 */
@Service
@Slf4j
public class DatasetService {

	@Autowired
	private HouseHoldService householdService;

	@Autowired
	private ProductService productService;

	@Autowired
	private TransactionService transactionService;

	/**
	 * 
	 * Loads CSV files to their respective entites
	 *
	 * @param householdFile
	 * @param productFile
	 * @param transactionFile
	 * @return Map<String, List<String>>
	 */
	public Map<String, List<String>> loadFiles(MultipartFile householdFile, MultipartFile productFile,
			MultipartFile transactionFile) {

		Map<String, List<String>> erroredRows = new HashMap<>();

		if(householdFile != null) {
			List<String> erroredHouseholdRows = loadHouseHoldFile(householdFile);

			if(erroredHouseholdRows != null && !erroredHouseholdRows.isEmpty())
				erroredRows.put("household", erroredHouseholdRows);
		}
		
		log.debug("Household data loaded");
		
		if(productFile != null) {
			List<String> erroredProductRows = loadProductFile(productFile);

			if(erroredProductRows != null && !erroredProductRows.isEmpty())
				erroredRows.put("product", erroredProductRows);
		}
		
		log.debug("Product data loaded");
		
		if(transactionFile != null) {
			List<String> erroredTransactionRows = loadTransactionFile(transactionFile);

			if(erroredTransactionRows != null && !erroredTransactionRows.isEmpty())
				erroredRows.put("transaction", erroredTransactionRows);
		}
		
		log.debug("Transaction data loaded");

		return erroredRows;
	}

	/**
	 * 
	 * Loads the transaction csv file to database table
	 *
	 * @param transactionFile
	 * @return List<String>
	 */
	private List<String> loadTransactionFile(MultipartFile transactionFile) {

		List<String> erroredTransactionRows = new ArrayList<>();

		List<String> fileRows = readFileContent(transactionFile, true);

		if(fileRows != null && !fileRows.isEmpty()) {
			erroredTransactionRows = transactionService.loadFileRows(fileRows);
		}

		return erroredTransactionRows;
	}

	/**
	 * 
	 * Loads the product csv file to database table
	 *
	 * @param transactionFile
	 * @return List<String>
	 */
	private List<String> loadProductFile(MultipartFile productFile) {
		
		List<String> erroredProductRows = new ArrayList<>();

		List<String> fileRows = readFileContent(productFile, false);

		if(fileRows != null && !fileRows.isEmpty()) {
			erroredProductRows = productService.loadFileRows(fileRows);
		}

		return erroredProductRows;
	}

	/**
	 * 
	 * Loads the household csv file to database table
	 *
	 * @param transactionFile
	 * @return List<String>
	 */
	private List<String> loadHouseHoldFile(MultipartFile householdFile) {

		List<String> erroredHouseholdRows = new ArrayList<>();

		List<String> fileRows = readFileContent(householdFile, false);

		if(fileRows != null && !fileRows.isEmpty()) {
			erroredHouseholdRows = householdService.loadFileRows(fileRows);
		}

		return erroredHouseholdRows;
	}

	/**
	 * 
	 * Reads the CSV file to List of strings
	 *
	 * @param householdFile
	 * @param skipTransactions
	 * @return List<String>
	 */
	private List<String> readFileContent(MultipartFile householdFile, boolean skipTransactions) {
		
		BufferedReader br;
		
		int skipCount = 0;
		
		List<String> fileContent = new ArrayList<>();
		
		try {
		
			String line;
			
			InputStream is = householdFile.getInputStream();
			
			br = new BufferedReader(new InputStreamReader(is));
			
			while ((line = br.readLine()) != null) {
				fileContent.add(line);
				skipCount++;
				
				if(skipTransactions && skipCount == 10000)
					break;
			}
			
		} catch (IOException e) {
			log.error("Error occured while transforming Household model to entity ::: " + e.getMessage());
			log.error("Error Stack trace ::: " + Arrays.toString(e.getStackTrace()));
		}

		return fileContent;
	}

	/**
	 * 
	 * Finds all the transaction entities based on household number
	 *
	 * @param hshdId
	 * @return List<TransactionModel> 
	 */
	public List<TransactionModel> fetchTransactions(int hshdId) {
		
		List<TransactionModel> transactionModels = new ArrayList<>();
		
		transactionModels = transactionService.fetchTransactionsOnHsHdId(hshdId);
		
		return transactionModels;
	}

}
