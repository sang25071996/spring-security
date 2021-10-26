package sang.uaa.com.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sang.uaa.com.vn.batch.StudentService;
import sang.uaa.com.vn.common.controller.BaseController;
import sang.uaa.com.vn.common.dto.ResponJson;

@RestController
@RequestMapping("/student")
public class StudentController extends BaseController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping()
	public ResponseEntity<ResponJson<Boolean>> insertBatch() {
		return getResponseEntity(studentService.insertBatch());
	}
}
