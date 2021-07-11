package sang.uaa.com.vn.product.entites;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import sang.uaa.com.vn.entites.BaseEntity;

@Entity
@Table(name = "PRODUCT_CATEGRORIES", schema = "PRODUCTS")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class ProductCategrories extends BaseEntity implements Serializable {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "categrories")
	private List<Product> products;
}
