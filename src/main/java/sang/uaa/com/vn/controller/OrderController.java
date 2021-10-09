package sang.uaa.com.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sang.uaa.com.vn.common.controller.BaseController;
import sang.uaa.com.vn.common.dto.RequestPagingBuilder;
import sang.uaa.com.vn.common.dto.ResponJson;
import sang.uaa.com.vn.dto.OrderDto;
import sang.uaa.com.vn.service.impl.OrderServiceImpl;

@RestController
@Validated
@RequestMapping("/orders")
public class OrderController extends BaseController {

	@Autowired
	private OrderServiceImpl orderService;

	@PostMapping()
	public ResponseEntity<ResponJson<Page<OrderDto>>> getPagination(@RequestBody RequestPagingBuilder<OrderDto> request) {
		return getResponseEntity(orderService.getPagination(request));
	}
}
