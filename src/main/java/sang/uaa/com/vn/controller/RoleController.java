package sang.uaa.com.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import sang.uaa.com.vn.common.controller.BaseController;
import sang.uaa.com.vn.common.dto.ResponJson;
import sang.uaa.com.vn.constant.Constants;
import sang.uaa.com.vn.dto.RoleDto;
import sang.uaa.com.vn.service.impl.RoleServiceImpl;

@Controller
@RequestMapping(Constants.ApiURL.API_ROLE)
public class RoleController extends BaseController {

	@Autowired
	private RoleServiceImpl roleService;
	
	@PostMapping()
	public ResponseEntity<ResponJson> save(@RequestBody RoleDto roleDto) {
		return getResponseEntity(roleService.save(roleDto));
	}

	@GetMapping()
	public ResponseEntity<ResponJson> getRoleById(Long id) {
		return getResponseEntity(roleService.getRoleById(id));
	}

	@PutMapping()
	public ResponseEntity<ResponJson> edit(@RequestBody RoleDto roleDto) {
		return getResponseEntity(roleService.edit(roleDto));
	}

}
