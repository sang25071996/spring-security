package sang.uaa.com.vn.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * <p>BaseEntity</p>
 * Nov 14, 2020
 *-------------------
 * @author macbook
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@MappedSuperclass
public class BaseEntity implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATETIME")
	private Date createdDateTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATETIME")
	private Date updatedDateTime;

	@Column(name = "UPDATED_BY")
	private String updatetedBy;

	@PrePersist
	public void createDate() {
		createdDateTime = new Date();
	}

	@PreUpdate
	public void updateDate() {
		updatedDateTime = new Date();
	}
}
