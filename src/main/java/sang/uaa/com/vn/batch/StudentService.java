package sang.uaa.com.vn.batch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sang.uaa.com.vn.entites.Student;
import sang.uaa.com.vn.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public boolean insertBatch() {
		List<Student> listStudent = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			listStudent.add(new Student("student" + i));
		}
		this.studentRepository.saveAll(listStudent);
		return true;
	}
	
}
