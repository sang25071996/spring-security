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
import sang.uaa.com.vn.dto.CategroryDto;
import sang.uaa.com.vn.service.CategroryService;

@RestController
@RequestMapping(Constants.ApiURL.API_CATEGRORY)
public class CategroryController extends BaseController {
	
	@Autowired
	private CategroryService categroryService;
	
	@PostMapping()
	public ResponseEntity<ResponJson<CategroryDto>> save(@Valid @RequestBody CategroryDto categroryDto) {
		return getResponseEntity(categroryService.create(categroryDto));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponJson<CategroryDto>> getById(@PathVariable Long id) {
		return getResponseEntity(categroryService.getById(id));
	}

	@PutMapping()
	public ResponseEntity<ResponJson<CategroryDto>> edit(@RequestBody CategroryDto categroryDto) {
		return getResponseEntity(categroryService.edit(categroryDto));
	}
	
	@DeleteMapping()
	public ResponseEntity<ResponJson<Boolean>> delete(@PathVariable Long id) {
		return getResponseEntity(categroryService.delete(id));
	}
}
