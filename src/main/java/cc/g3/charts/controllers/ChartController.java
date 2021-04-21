package cc.g3.charts.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.g3.charts.models.FirstChart;
import cc.g3.charts.models.FourthChart;
import cc.g3.charts.models.SecondChart;
import cc.g3.charts.models.ThirdChart;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * Rest Controller to pull charts data
 *
 */
@RestController
@RequestMapping("charts")
public class ChartController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	 * Rest controller Mapping method to retrieve data for first chart
	 * 
	 * @return List<FirstChart>
	 * 
	 */
	@GetMapping("first")
	public List<FirstChart> getFirstChart(){
		List<FirstChart> firstCharts = jdbcTemplate.query(FirstChart.QUERY, new RowMapper<FirstChart>() {

			@Override
			public FirstChart mapRow(ResultSet rs, int rowNum) throws SQLException {
				FirstChart firstChart = new FirstChart();
				
				firstChart.setYear(rs.getInt("year"));
				firstChart.setTotalExpenditure(rs.getDouble("totalExpenditure"));
				
				return firstChart;
			}
			
		});
		
	    return firstCharts;
	}
	
	/**
	 * 
	 * Rest controller Mapping method to retrieve data for second chart
	 *
	 * @return List<SecondChart>
	 */
	@GetMapping("second")
	public List<SecondChart> getSecondChart(){
		List<SecondChart> secondCharts = jdbcTemplate.query(SecondChart.QUERY, new RowMapper<SecondChart>() {

			@Override
			public SecondChart mapRow(ResultSet rs, int rowNum) throws SQLException {
				SecondChart secondChart = new SecondChart();
				
				secondChart.setYear(rs.getInt("year"));
				secondChart.setDepartment(rs.getString("department"));
				secondChart.setTotalExpenditure(rs.getDouble("totalExpenditure"));
				
				return secondChart;
			}
			
		});
		
	    return secondCharts;
	}
	
	/**
	 * 
	 * Rest controller Mapping method to retrieve data for third chart
	 *
	 * @return List<ThirdChart>
	 */
	@GetMapping("third")
	public List<ThirdChart> getThirdChart(){
		
		double totalSpend = jdbcTemplate.queryForObject("select round(sum(spend), 2) from transactions;", Double.class);
		
		List<ThirdChart> thirdCharts = jdbcTemplate.query(ThirdChart.QUERY, new PreparedStatementSetter() {
	            
				public void setValues(PreparedStatement preparedStatement) throws SQLException {
	              preparedStatement.setDouble(1, totalSpend);
	              preparedStatement.setDouble(2, totalSpend);
	              preparedStatement.setDouble(3, totalSpend);
	        	}
				
        	}, new RowMapper<ThirdChart>() {

				@Override
				public ThirdChart mapRow(ResultSet rs, int rowNum) throws SQLException {
					ThirdChart thirdChart = new ThirdChart();
					
					thirdChart.setAgeRange(rs.getString("ageRange"));
					thirdChart.setSpendPercentage(rs.getDouble("spendPercentage"));
					
					return thirdChart;
				}
			
		});
		
	    return thirdCharts;
	}
	
	/**
	 * 
	 * Rest controller Mapping method to retrieve data for fourth chart
	 *
	 * @return List<FourthChart>
	 */
	@GetMapping("fourth")
	public List<FourthChart> getFourthChart(){
		List<FourthChart> fourthCharts = jdbcTemplate.query(FourthChart.QUERY, new RowMapper<FourthChart>() {

			@Override
			public FourthChart mapRow(ResultSet rs, int rowNum) throws SQLException {
				FourthChart fourthChart = new FourthChart();
				
				fourthChart.setIncomeRange(rs.getString("incomeRange"));
				fourthChart.setChildren(rs.getString("children"));
				fourthChart.setTotalExpenditure(rs.getDouble("totalExpenditure"));
				
				return fourthChart;
			}
			
		});
		
	    return fourthCharts;
	}
	
	
}
