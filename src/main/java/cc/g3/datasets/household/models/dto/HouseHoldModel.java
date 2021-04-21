package cc.g3.datasets.household.models.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * House hold data transfer object
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class HouseHoldModel {

	@JsonProperty
	private int id;
	
	@JsonProperty
	private int hshdNum;

	@JsonProperty
	private boolean loyaltyFlag;

	@JsonProperty
	private String ageRange;

	@JsonProperty
	private String maritalStatus;

	@JsonProperty
	private String incomeRange;

	@JsonProperty
	private String homeOwnerStatus;

	@JsonProperty
	private String houseHoldComposition;

	@JsonProperty
	private String houseHoldSize;

	@JsonProperty
	private String childrenCount;

//	@JsonProperty
//	@Getter(value = AccessLevel.NONE)
//	private Set<Integer> transactionIds;
//
//	public Set<Integer> getTransactionsIds() {
//		
//		if(transactionIds == null)
//			return new HashSet<>();
//		
//		return transactionIds;
//	}
}
