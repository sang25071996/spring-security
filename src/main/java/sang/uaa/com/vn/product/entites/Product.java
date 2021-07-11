package sang.uaa.com.vn.product.entites;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.collections4.map.HashedMap;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import sang.uaa.com.vn.entites.BaseEntity;

@Entity
@Table(name = "PRODUCT", schema = "PRODUCTS")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TypeDefs({
	  @TypeDef(name = "json", typeClass = JsonStringType.class),
	  @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
	})
public class Product extends BaseEntity implements Serializable {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Type(type = "jsonb")
	@Column(name = "PROPERTIES", columnDefinition = "jsonb")
	private Map<String, Object> properties = new HashedMap<>();
	
	@ManyToOne()
	@JoinColumn(name = "CATEGRORIE_ID")
	private ProductCategrories categrories;
}
