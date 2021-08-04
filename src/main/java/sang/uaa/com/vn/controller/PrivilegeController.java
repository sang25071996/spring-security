package sang.uaa.com.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sang.uaa.com.vn.common.controller.BaseController;
import sang.uaa.com.vn.common.dto.ResponJson;
import sang.uaa.com.vn.constant.Constants;
import sang.uaa.com.vn.dto.PrivilegeDto;
import sang.uaa.com.vn.service.PrivilegeService;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.ApiURL.API_PRIVILEGE)
public class PrivilegeController extends BaseController {

	@Autowired
	private PrivilegeService privilegeService;

	public PrivilegeDto getById(Long id) {
		return privilegeService.getById(id);
	}

	public PrivilegeDto create(PrivilegeDto privilegeDto) {
		return privilegeService.create(privilegeDto);
	}

	@PostMapping()
	public ResponseEntity<ResponJson<PrivilegeDto>> save(@Valid @RequestBody PrivilegeDto privilegeDto) {
		return getResponseEntity(privilegeService.create(privilegeDto));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponJson<PrivilegeDto>> getRoleById(@PathVariable Long id) {
		return getResponseEntity(privilegeService.getById(id));
	}

	@PutMapping()
	public ResponseEntity<ResponJson<PrivilegeDto>> edit(@RequestBody PrivilegeDto privilegeDto) {
		return getResponseEntity(privilegeService.edit(privilegeDto));
	}
	
	@DeleteMapping()
	public ResponseEntity<ResponJson<Boolean>> delete(@PathVariable Long id) {
		return getResponseEntity(privilegeService.delete(id));
	}

}
