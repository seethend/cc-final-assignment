package cc.g3.charts.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Third chart model
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThirdChart {

	public static final String QUERY = "select "
			+ " age_range as ageRange, round((round(sum(spend), 2)/?) * 100, 2) as spendPercentage\r\n"
			+ " from transactions t \r\n"
			+ " inner join products p on t.product_num = p.product_num \r\n"
			+ " inner join households h on h.hshd_num = t.hshd_num \r\n"
			+ " where age_range <> 'null' "
			+ " group by age_range "
			+ " having round((round(sum(spend), 2)/?) * 100, 2) > 0.05"
			+ " order by round((round(sum(spend), 2)/?) * 100, 2) desc "
//			+ " limit 10; "
			;
	
	private String ageRange;
//	private String commodity;
	private double spendPercentage;
	
}
