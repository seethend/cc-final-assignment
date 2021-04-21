package cc.g3.charts.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * First chart model
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FirstChart {
	
	public static final String QUERY = "select year "
			+ " , round(sum(spend), 2) as totalExpenditure \r\n"
			+ " from transactions t \r\n"
			+ " inner join products p on t.product_num = p.product_num \r\n"
			+ " inner join households h on h.hshd_num = t.hshd_num \r\n"
			+ " group by year \r\n"
			+ " order by 1;";
	
	private int year;
	private double totalExpenditure;
}
