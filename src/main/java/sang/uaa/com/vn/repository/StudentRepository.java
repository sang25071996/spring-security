package sang.uaa.com.vn.repository;

import org.springframework.data.repository.CrudRepository;

import sang.uaa.com.vn.entites.Student;

//@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
	
}
