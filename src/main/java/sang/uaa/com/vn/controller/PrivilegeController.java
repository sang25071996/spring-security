package sang.uaa.com.vn.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sang.uaa.com.vn.common.controller.BaseController;
import sang.uaa.com.vn.common.dto.ResponJson;
import sang.uaa.com.vn.constant.Constants;
import sang.uaa.com.vn.dto.PrivilegeDto;
import sang.uaa.com.vn.service.PrivilegeService;

@RestController
@RequestMapping(Constants.ApiURL.API_PRIVILEGE)
public class PrivilegeController extends BaseController {

	@Autowired
	private PrivilegeService privilegeService;
	
	@PostMapping()
	public ResponseEntity<ResponJson> save(@Valid @RequestBody PrivilegeDto privilegeDto) {
		return getResponseEntity(privilegeService.create(privilegeDto));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponJson> getRoleById(@PathVariable Long id) {
		return getResponseEntity(privilegeService.getById(id));
	}

	@PutMapping()
	public ResponseEntity<ResponJson> edit(@RequestBody PrivilegeDto privilegeDto) {
		return getResponseEntity(privilegeService.edit(privilegeDto));
	}
	
	@DeleteMapping()
	public ResponseEntity<ResponJson> delete(@PathVariable Long id) {
		return getResponseEntity(privilegeService.delete(id));
	}

}
