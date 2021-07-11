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
import sang.uaa.com.vn.dto.ProductCategroriesDto;
import sang.uaa.com.vn.service.ProductCategroriesService;

@RestController
@RequestMapping(Constants.ApiURL.API_PRODUCT_CATEGRORIES)
public class ProductCategroriesController extends BaseController {
	
	@Autowired
	private ProductCategroriesService productCategroriesService;
	
	@PostMapping()
	public ResponseEntity<ResponJson<ProductCategroriesDto>> save(@Valid @RequestBody ProductCategroriesDto productCategroriesDto) {
		return getResponseEntity(productCategroriesService.create(productCategroriesDto));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponJson<ProductCategroriesDto>> getById(@PathVariable Long id) {
		return getResponseEntity(productCategroriesService.getById(id));
	}

	@PutMapping()
	public ResponseEntity<ResponJson<ProductCategroriesDto>> edit(@RequestBody ProductCategroriesDto productCategroriesDto) {
		return getResponseEntity(productCategroriesService.edit(productCategroriesDto));
	}
	
	@DeleteMapping()
	public ResponseEntity<ResponJson<Boolean>> delete(@PathVariable Long id) {
		return getResponseEntity(productCategroriesService.delete(id));
	}
}
