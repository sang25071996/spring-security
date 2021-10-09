package sang.uaa.com.vn.product.entites;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sang.uaa.com.vn.entites.BaseEntity;

@Entity
@Table(name = "ORDER", schema = "PRODUCTS")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class Order extends BaseEntity implements Serializable {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "PRODUCT_ID")
	private Long product;
}
