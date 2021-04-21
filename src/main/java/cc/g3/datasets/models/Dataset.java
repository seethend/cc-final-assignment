package cc.g3.datasets.models;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Dataset to pick up CSV files
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class Dataset {

	@JsonProperty
	private MultipartFile householdFile;
	
	@JsonProperty
	private MultipartFile productFile;
	
	@JsonProperty
	private MultipartFile transactionFile;
	
}
