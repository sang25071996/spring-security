package sang.uaa.com.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
		return getResponseEntity(userService.createUser(userDto));
	}
	
	@GetMapping(path = "/id")
	public ResponseEntity<ResponJson> getRoleId(Long id) {
		return getResponseEntity(roleService.getRoleById(id));
	}

	@GetMapping()
	public ResponseEntity<ResponJson> findAllUser() {
		
		return getResponseEntity(userService.findAllUser());
	}
}
