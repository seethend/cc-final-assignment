package cc.g3.datasets.services;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Utility Service to perform validations
 *
 */
@Slf4j
public class UtilityService {

	/**
	 * 
	 * Validate the string column
	 *
	 * @param columns
	 * @param index
	 * @return boolean
	 */
	public static boolean checkAndFetchValue(String[] columns, int index) {
		
		if(index > columns.length)
			return false;
		
		return true;
	}

	/**
	 * 
	 * Validate the integer column
	 *
	 * @param columns
	 * @param index
	 * @return boolean
	 */
	public static boolean checkAndFetchIntegerValue(String[] columns, int index) {
		
		if(!checkAndFetchValue(columns, index))
			return false;
		
		try {
			Integer.parseInt(columns[index].trim());
		} catch (Exception e) {
			log.error("Error occured while parsing string to integer ::: " + e.getMessage());
			return false;
		}
		
		return true;
	}

	/**
	 * 
	 * Validate the double column
	 *
	 * @param columns
	 * @param index
	 * @return boolean
	 */
	public static boolean checkAndFetchDoubleValue(String[] columns, int index) {
		if(!checkAndFetchValue(columns, index))
			return false;
		
		try {
			Double.parseDouble(columns[index].trim());
		} catch (Exception e) {
			log.error("Error occured while parsing string to integer ::: " + e.getMessage());
			return false;
		}
		
		return true;
	}
	
}
