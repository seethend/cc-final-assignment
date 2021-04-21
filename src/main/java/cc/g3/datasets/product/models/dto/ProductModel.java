package cc.g3.datasets.product.models.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Product data transfer object
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class ProductModel {

	@JsonProperty
	private int id;
	
	@JsonProperty
	private int productNum;

	@JsonProperty
	private String department;

	@JsonProperty
	private String commodity;

	@JsonProperty
	private String brandType;

	@JsonProperty
	private boolean naturalOrganicFlag;

//	@JsonProperty
//	@Getter(value = AccessLevel.NONE)
//	private Set<Integer> transactionIds;
//
//	public Set<Integer> getTransactionIds() {
//		return transactionIds;
//	}
	
}
