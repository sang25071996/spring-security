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
import sang.uaa.com.vn.dto.ProductDto;
import sang.uaa.com.vn.service.ProductService;

@RestController
@RequestMapping(Constants.ApiURL.API_PRODUCT)
public class ProductController extends BaseController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping()
	public ResponseEntity<ResponJson<ProductDto>> save(@Valid @RequestBody ProductDto productDto) {
		return getResponseEntity(productService.create(productDto));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponJson<ProductDto>> getById(@PathVariable Long id) {
		return getResponseEntity(productService.getById(id));
	}

	@PutMapping()
	public ResponseEntity<ResponJson<ProductDto>> edit(@RequestBody ProductDto productDto) {
		return getResponseEntity(productService.edit(productDto));
	}
	
	@DeleteMapping()
	public ResponseEntity<ResponJson<Boolean>> delete(@PathVariable Long id) {
		return getResponseEntity(productService.delete(id));
	}
}
