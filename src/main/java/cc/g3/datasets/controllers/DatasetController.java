package cc.g3.datasets.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.g3.datasets.models.Dataset;
import cc.g3.datasets.services.DatasetService;
import cc.g3.datasets.transaction.models.dto.TransactionModel;

/**
 * 
 * Rest Controller to load csv files and retrieve transactions data based on house hold number
 *
 */
@RestController
@RequestMapping("v1/dataset/")
public class DatasetController {

	@Autowired
	private DatasetService datasetService;
	
	/**
	 *  
	 * Rest controller mapping to load CSV files
	 *
	 * @param dataset
	 * @return ResponseEntity<Map<String, List<String>>>
	 */
	@PostMapping(value = "load", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Map<String, List<String>>> loadDataSet(
			@ModelAttribute Dataset dataset) {
		
		Map<String, List<String>> erroredRows = 
				datasetService.loadFiles(dataset.getHouseholdFile(), dataset.getProductFile(), dataset.getTransactionFile());
		
		return new ResponseEntity<Map<String,List<String>>>(erroredRows, HttpStatus.OK);
	}
	
	/**
	 * 
	 * Rest controller mapping to pull the transactions based on household number
	 *
	 * @param hshdId
	 * @return ResponseEntity<List<TransactionModel>>
	 */
	@GetMapping("transactions/{hshdId}")
	public ResponseEntity<List<TransactionModel>> fetchTransactions(
			@PathVariable int hshdId) {
		
		List<TransactionModel> transactionModels = datasetService.fetchTransactions(hshdId);
		
		if(transactionModels == null)
			transactionModels = new ArrayList<>();
		
		return new ResponseEntity<List<TransactionModel>>(transactionModels, HttpStatus.OK);		
	}
	
}
