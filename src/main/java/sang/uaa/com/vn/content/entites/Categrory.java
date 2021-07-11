package sang.uaa.com.vn.content.entites;

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
@Table(name = "CATEGRORY", schema = "CONTENTS")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Categrory extends BaseEntity implements Serializable {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "categrory")
	private List<Post> posts;
	
}
