package cc.g3.datasets.transaction.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import cc.g3.datasets.household.models.HouseHold;
import cc.g3.datasets.product.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Transaction Table Entity 
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "transaction_id")
	private int id;

	@Column(name = "basket_num", nullable = true)
	private int basketNum;
	
	@ManyToOne
    @JoinColumn(name="hshd_num", referencedColumnName = "hshd_num", nullable=false)
	private HouseHold houseHold;
	
	@Column(name = "date", nullable = true)
	private Date purchaseDate;

	@ManyToOne
    @JoinColumn(name="product_num", referencedColumnName = "product_num", nullable=false)
	private Product product;

	@Column(name = "spend", nullable = true)
	private double spend;

	@Column(name = "units", nullable = true)
	private int units;

	@Column(name = "store_region", nullable = true)
	private String storeRegion;

	@Column(name = "week_num", nullable = true)
	private int weekNum;

	@Column(name = "year", nullable = true)
	private int year;	
	
}
