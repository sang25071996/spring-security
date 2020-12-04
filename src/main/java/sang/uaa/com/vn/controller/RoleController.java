package sang.uaa.com.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import sang.uaa.com.vn.common.ResponJson;
import sang.uaa.com.vn.common.controller.BaseController;
import sang.uaa.com.vn.constant.Constants;
import sang.uaa.com.vn.dto.RoleDto;
import sang.uaa.com.vn.service.impl.RoleServiceImpl;
import sang.uaa.com.vn.utils.exception.BadRequestException;
import sang.uaa.com.vn.utils.exception.NotFoundException;

@Controller
@RequestMapping(Constants.ApiURL.API_ROLE)
public class RoleController extends BaseController {

	@Autowired
	private RoleServiceImpl roleService;
	
	@PostMapping()
	public ResponseEntity<ResponJson> save(@RequestBody RoleDto roleDto) throws BadRequestException {
		return getResponseEntity(roleService.save(roleDto));
	}

	@GetMapping()
	public ResponseEntity<ResponJson> getRoleById(Long id) throws NotFoundException {
		return getResponseEntity(roleService.getRoleById(id));
	}

	@PutMapping()
	public ResponseEntity<ResponJson> edit(@RequestBody RoleDto roleDto) throws NotFoundException {
		return getResponseEntity(roleService.edit(roleDto));
	}

}
