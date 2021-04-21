package cc.g3.datasets.household.models;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * House hold Table Entity 
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "households")
public class HouseHold implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private int id;
	
	@Column(name = "hshd_num")
	private int hshdNum;
	
	@Column(name = "loyalty_flag", nullable = true)
	private boolean loyaltyFlag;
	
	@Column(name = "age_range", nullable = true)
	private String ageRange;
	
	@Column(name = "marital_status", nullable = true)
	private String maritalStatus;
	
	@Column(name = "income_range", nullable = true)
	private String incomeRange;
	
	@Column(name = "homeowner_desc", nullable = true)
	private String homeOwnerStatus;
	
	@Column(name = "hshd_composition", nullable = true)
	private String houseHoldComposition;
	
	@Column(name = "hshd_size", nullable = true)
	private String houseHoldSize;
	
	@Column(name = "children", nullable = true)
	private String childrenCount;
	
}
