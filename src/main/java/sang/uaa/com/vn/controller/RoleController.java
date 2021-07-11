package sang.uaa.com.vn.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sang.uaa.com.vn.common.controller.BaseController;
import sang.uaa.com.vn.common.dto.RequestPagingBuilder;
import sang.uaa.com.vn.common.dto.ResponJson;
import sang.uaa.com.vn.constant.Constants;
import sang.uaa.com.vn.dto.RoleDto;
import sang.uaa.com.vn.dto.SystemPermission;
import sang.uaa.com.vn.service.impl.RoleServiceImpl;

@RestController
@Validated
@RequestMapping(Constants.ApiURL.API_ROLE)
public class RoleController extends BaseController {

	@Autowired
	private RoleServiceImpl roleService;
	
	@PostMapping()
	public ResponseEntity<ResponJson<RoleDto>> save(@Valid @RequestBody RoleDto roleDto, BindingResult bindingResult) {
		return getResponseEntity(roleService.create(roleDto));
	}

	@GetMapping()
	public ResponseEntity<ResponJson<List<RoleDto>>> getRoles() {
		return getResponseEntity(roleService.getRoles());
	}
	
	@PostMapping("filter-paging")
	public ResponseEntity<ResponJson<Page<RoleDto>>> filterPaging(@RequestBody RequestPagingBuilder<RoleDto> requestPagingBuilder) {
		return getResponseEntity(roleService.filterPaging(requestPagingBuilder));
	}
	
	@PostMapping("system-permission")
	public ResponseEntity<ResponJson<SystemPermission>> setManagementPermission(@RequestBody SystemPermission systemPermission) {
		return getResponseEntity(roleService.setManagementPermission(systemPermission));
	}
	
	@GetMapping("system-permission")
	public ResponseEntity<ResponJson<List<SystemPermission>>> getManagementPermission() {
		return getResponseEntity(roleService.getManagementPermission());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponJson<RoleDto>> getRoleById(@PathVariable Long id) {
		return getResponseEntity(roleService.getById(id));
	}

	@PutMapping()
	public ResponseEntity<ResponJson<RoleDto>> edit(@RequestBody RoleDto roleDto) {
		return getResponseEntity(roleService.edit(roleDto));
	}
	
	@DeleteMapping()
	public ResponseEntity<ResponJson<Boolean>> delete(@PathVariable Long id) {
		return getResponseEntity(roleService.delete(id));
	}

}
