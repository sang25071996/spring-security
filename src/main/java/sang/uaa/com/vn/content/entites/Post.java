package sang.uaa.com.vn.content.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vladmihalcea.hibernate.type.array.StringArrayType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "POST", schema = "CONTENTS")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TypeDefs({
    @TypeDef(name = "string-array", typeClass = StringArrayType.class)
})
public class Post {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Type(type = "string-array")
	@Column(name = "paragraphs", columnDefinition = "text[]")
	private String[] paragraphs;
	
	@ManyToOne()
	@JoinColumn(name = "CATEGRORY_ID")
	private Categrory categrory;
}
