package sang.uaa.com.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import sang.uaa.com.vn.common.controller.BaseController;
import sang.uaa.com.vn.common.dto.ResponJson;
import sang.uaa.com.vn.dto.UserDto;
import sang.uaa.com.vn.service.RoleService;
import sang.uaa.com.vn.service.UserService;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	RoleService roleService;
	@PostMapping()
	public ResponseEntity<ResponJson> createUser(@RequestBody UserDto userDto) {
		return getResponseEntity(userService.create(userDto));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponJson> getById(@PathVariable Long id) {
		return getResponseEntity(userService.getById(id));
	}
	
	@DeleteMapping()
	public ResponseEntity<ResponJson> delete(@PathVariable Long id) {
		return getResponseEntity(userService.delete(id));
	}

	@GetMapping()
	public ResponseEntity<ResponJson> getUsers() {
		
		return getResponseEntity(userService.getUsers());
	}
}
