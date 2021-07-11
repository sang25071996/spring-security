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
import sang.uaa.com.vn.dto.PostDto;
import sang.uaa.com.vn.service.PostService;

@RestController
@RequestMapping(Constants.ApiURL.API_POST)
public class PostController extends BaseController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping()
	public ResponseEntity<ResponJson<PostDto>> save(@Valid @RequestBody PostDto postDto) {
		return getResponseEntity(postService.create(postDto));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponJson<PostDto>> getById(@PathVariable Long id) {
		return getResponseEntity(postService.getById(id));
	}

	@PutMapping()
	public ResponseEntity<ResponJson<PostDto>> edit(@RequestBody PostDto postDto) {
		return getResponseEntity(postService.edit(postDto));
	}
	
	@DeleteMapping()
	public ResponseEntity<ResponJson<Boolean>> delete(@PathVariable Long id) {
		return getResponseEntity(postService.delete(id));
	}
}
