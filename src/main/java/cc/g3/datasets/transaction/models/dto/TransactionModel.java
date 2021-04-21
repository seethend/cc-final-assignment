package cc.g3.datasets.transaction.models.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import cc.g3.datasets.household.models.dto.HouseHoldModel;
import cc.g3.datasets.product.models.dto.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Transaction data transfer object
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionModel {

	@JsonProperty
	private int id;

	@JsonProperty
	private int basketNum;

	@JsonProperty
	private HouseHoldModel houseHoldModel;

	@JsonProperty
	@JsonFormat(pattern="dd-mm-yyyy")
	private Date purchaseDate;

	@JsonProperty
	private ProductModel productModel;

	@JsonProperty
	private double spend;

	@JsonProperty
	private int units;

	@JsonProperty
	private String storeRegion;

	@JsonProperty
	private int weekNum;

	@JsonProperty
	private int year;
	
}
