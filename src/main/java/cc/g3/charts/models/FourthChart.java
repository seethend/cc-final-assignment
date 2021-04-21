package cc.g3.charts.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Fourth chart model
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FourthChart {

	public static final String QUERY = " select income_range as incomeRange, children, round(sum(spend), 2) as totalExpenditure \r\n"
			+ " from transactions t \r\n"
			+ " inner join products p on t.product_num = p.product_num \r\n"
			+ " inner join households h on h.hshd_num = t.hshd_num \r\n"
			+ " where income_range <> 'null' and children <> 'null' \r\n"
			+ " group by income_range, children, hshd_size \r\n"
			+ " order by round(sum(spend), 2) desc; ";
	
	private String incomeRange;
	private String children;
	private double totalExpenditure;
}
