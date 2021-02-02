package sang.uaa.com.vn.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sang.uaa.com.vn.entites.Student;
import sang.uaa.com.vn.repository.StudentRepository;

@Service
public class StudentService {
	private static final Logger LOG = LoggerFactory.getLogger(StudentService.class);
	@Autowired
	private StudentRepository studentRepository;
	
//	@Transactional(rollbackOn = Exception.class)
	public boolean insertBatch() {
		List<Student> listStudent = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			listStudent.add(new Student("student" + i));
		}
		this.studentRepository.saveAll(listStudent);
		return true;
	}
	
}
