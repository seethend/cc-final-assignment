package cc.g3.datasets.household.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.g3.datasets.household.models.HouseHold;
import cc.g3.datasets.household.models.dto.HouseHoldMapper;
import cc.g3.datasets.household.models.dto.HouseHoldModel;
import cc.g3.datasets.household.repositories.HouseHoldRepository;
import lombok.extern.slf4j.Slf4j;

import cc.g3.datasets.services.UtilityService;

/**
 * 
 * Household service layers 
 *
 */
@Service
@Slf4j
public class HouseHoldService {
	
	@Autowired
	private HouseHoldRepository houseHoldRepository;

	/**
	 * 
	 * Saves the house entity using the data access layer
	 *
	 * @param houseHold
	 * @return HouseHold
	 */
	public HouseHold saveHouseHold(HouseHold houseHold) {
		
		HouseHold savedHouseHold = null;
		
		try {
			savedHouseHold = houseHoldRepository.save(houseHold);
		} catch(Exception e) {
			log.error("Error while saving the household entity ::: " + e.getMessage());
			log.error("Error stack ::: " + Arrays.toString(e.getStackTrace()));
		}
		
		return savedHouseHold;
	}
	
	/**
	 * 
	 * Saves list of Households entities to database
	 *
	 * @param houseHolds
	 * @return List<HouseHold>
	 */
	public List<HouseHold> saveAllHouseHolds(List<HouseHold> houseHolds) {
		
		List<HouseHold> savedHouseHolds = null;
		
		try {
			savedHouseHolds = houseHoldRepository.saveAll(houseHolds);
		} catch(Exception e) {
			log.error("Error while saving the household entity ::: " + e.getMessage());
			log.error("Error stack ::: " + Arrays.toString(e.getStackTrace()));
		}
		
		return savedHouseHolds;
	}
	
	/**
	 * 
	 * Converts household DTO to entity and saves the entity to database
	 *
	 * @param houseHoldModel
	 * @return
	 */
	public HouseHoldModel saveHouseHoldModel(HouseHoldModel houseHoldModel) {
		
		HouseHoldModel savedHouseHoldModel = null;
		
		try {
			
			HouseHold houseHold = HouseHoldMapper.toEntity(houseHoldModel);
					
			houseHold = saveHouseHold(houseHold);
			
			if(houseHold != null)
				savedHouseHoldModel = HouseHoldMapper.toModel(houseHold);
			
		} catch(Exception e) {
			log.error("Error while saving the household model ::: " + e.getMessage());
			log.error("Error stack ::: " + Arrays.toString(e.getStackTrace()));
		}
		
		return savedHouseHoldModel;
	}

	/**
	 * 
	 * Splits the List of CSV records and creates the list of household entities
	 *
	 * @param fileRows
	 * @return List<String>
	 */
	public List<String> loadFileRows(List<String> fileRows) {
		List<String> erroredHouseholdRows = new ArrayList<>();
		List<HouseHold> houseHoldEntities = new ArrayList<>();
		 
		for(int i = 1; i < fileRows.size(); i++) {
			String record = fileRows.get(i);
			
			if(record != null && !record.trim().isEmpty()) {
				String[] columns = record.split(",");
				
				HouseHold houseHold = new HouseHold();
				
				if(UtilityService.checkAndFetchIntegerValue(columns, 0) && UtilityService.checkAndFetchValue(columns, 1) && UtilityService.checkAndFetchValue(columns, 2) && 
						UtilityService.checkAndFetchValue(columns, 3) && UtilityService.checkAndFetchValue(columns, 4) && UtilityService.checkAndFetchValue(columns, 5) && 
						UtilityService.checkAndFetchValue(columns, 6) && UtilityService.checkAndFetchValue(columns, 7) && UtilityService.checkAndFetchValue(columns, 8)) {

					houseHold.setHshdNum(Integer.parseInt(columns[0].trim()));
					houseHold.setLoyaltyFlag(columns[1].trim().equals("Y") ? true : false);
					houseHold.setAgeRange(columns[2].trim());
					houseHold.setMaritalStatus(columns[3].trim());
					houseHold.setIncomeRange(columns[4].trim());
					houseHold.setHomeOwnerStatus(columns[5].trim());
					houseHold.setHouseHoldComposition(columns[6].trim());
					houseHold.setHouseHoldSize(columns[7].trim());
					houseHold.setChildrenCount(columns[8].trim());
					
					houseHoldEntities.add(houseHold);
				} else {
					erroredHouseholdRows.add(record);
				}
			}
		}
		
		saveAllHouseHolds(houseHoldEntities);
		
		return erroredHouseholdRows;
	}

	/**
	 * 
	 * Finds house hold entity based on household number
	 *
	 * @param hshdId
	 * @return HouseHold
	 */
	public HouseHold getHouseHoldById(int hshdId) {
		
		Optional<HouseHold> optionalHouseHold = houseHoldRepository.findByHshdNum(hshdId);
		
		if(optionalHouseHold.isPresent())
			return optionalHouseHold.get();
		
		log.error("House hold entity doesn't exists for id " + hshdId);
		
		return null;
	}
}
