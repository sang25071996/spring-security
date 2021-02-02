package sang.uaa.com.vn.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "STUDENT", schema = "ENTERPRISE")
public class Student {
	
	public Student(String name) {
		this.name = name;
	}
	@Id
	@SequenceGenerator(name="student_id_seq",sequenceName="student_id_seq", allocationSize=20, schema = "ENTERPRISE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="student_id_seq")
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
}
