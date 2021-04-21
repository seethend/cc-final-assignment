package cc.g3.datasets.product.models;


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
 * Product Table Entity 
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7249530123498443758L;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "id")
	private int id;
	
	@Column(name = "product_num")
	private int productNum;

	@Column(name = "department", nullable = true)
	private String department;

	@Column(name = "commodity", nullable = true)
	private String commodity;

	@Column(name = "brand_type", nullable = true)
	private String brandType;

	@Column(name = "natural_organic_flag", nullable = true)
	private boolean naturalOrganicFlag;
	
//	@OneToMany(mappedBy = "product")
//    private Set<Transaction> transactions;

	
}
