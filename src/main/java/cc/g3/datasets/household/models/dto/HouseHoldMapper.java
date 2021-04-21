package cc.g3.datasets.household.models.dto;

import java.util.Arrays;

import cc.g3.datasets.household.models.HouseHold;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * House hold entity to data transfer object mapper
 *
 */
@Slf4j
public class HouseHoldMapper {

	
	/**
	 * 
	 * House hold DTO to entity mapper
	 *
	 * @param houseHoldModel
	 * @return HouseHold
	 */
	public static HouseHold toEntity(HouseHoldModel houseHoldModel) {
		
		HouseHold houseHold = new HouseHold();
		
		try {
			
			houseHold.setId(houseHoldModel.getId());
			houseHold.setHshdNum(houseHoldModel.getHshdNum());
			houseHold.setLoyaltyFlag(houseHoldModel.isLoyaltyFlag());
			houseHold.setAgeRange(houseHoldModel.getAgeRange());
			houseHold.setMaritalStatus(houseHoldModel.getMaritalStatus());
			houseHold.setIncomeRange(houseHoldModel.getIncomeRange());
			houseHold.setHomeOwnerStatus(houseHoldModel.getHomeOwnerStatus());
			houseHold.setHouseHoldComposition(houseHoldModel.getHouseHoldComposition());
			houseHold.setHouseHoldSize(houseHoldModel.getHouseHoldSize());
			houseHold.setChildrenCount(houseHoldModel.getChildrenCount());
			
		} catch(Exception e) {
			log.error("Error occured while transforming Household model to entity ::: " + e.getMessage());
			log.error("Error Stack trace ::: " + Arrays.toString(e.getStackTrace()));
			return null;
		}
		
		return houseHold;
	}
	
	/**
	 * 
	 * House hold entity to DTO mapper
	 *
	 * @param houseHold
	 * @return HouseHoldModel
	 */
	public static HouseHoldModel toModel(HouseHold houseHold) {
	
		HouseHoldModel houseHoldModel = new HouseHoldModel();
		
		try {

			houseHoldModel.setId(houseHold.getId());
			houseHoldModel.setHshdNum(houseHold.getHshdNum());
			houseHoldModel.setLoyaltyFlag(houseHold.isLoyaltyFlag());
			houseHoldModel.setAgeRange(houseHold.getAgeRange());
			houseHoldModel.setMaritalStatus(houseHold.getMaritalStatus());
			houseHoldModel.setIncomeRange(houseHold.getIncomeRange());
			houseHoldModel.setHomeOwnerStatus(houseHold.getHomeOwnerStatus());
			houseHoldModel.setHouseHoldComposition(houseHold.getHouseHoldComposition());
			houseHoldModel.setHouseHoldSize(houseHold.getHouseHoldSize());
			houseHoldModel.setChildrenCount(houseHold.getChildrenCount());
			
//			if(houseHold.getTransactions() != null)
//				houseHoldModel.setTransactionIds(
//						houseHold.getTransactions()
//							.stream()
//							.map(p -> p.getId()).collect(Collectors.toSet())
//				);
			
		} catch(Exception e) {
			log.error("Error occured while transforming Household entity to model ::: " + e.getMessage());
			log.error(Arrays.toString(e.getStackTrace()));
			return null;
		}
		
		return houseHoldModel;
	}
	
}
