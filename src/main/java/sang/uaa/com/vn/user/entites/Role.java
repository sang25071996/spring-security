package sang.uaa.com.vn.user.entites;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sang.uaa.com.vn.entites.BaseEntity;

@Entity
@Table(name = "ROLE", schema = "USERS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role extends BaseEntity {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME", unique = true)
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USER_ROLE", schema = "USERS", joinColumns = {
			@JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	private Set<User> users = new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ROLES_PRIVILEGES", schema = "USERS", joinColumns = {
			@JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "PRIVILEGE_ID") })
	private Set<Privilege> privileges;
	
	public Role(Long id, String name) {
		this.id = id;
		this.name = name;
	}
}
