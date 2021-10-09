package sang.uaa.com.vn.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import sang.uaa.com.vn.common.dto.RequestPagingBuilder;
import sang.uaa.com.vn.dto.OrderDto;
import sang.uaa.com.vn.mapper.OrderMapper;
import sang.uaa.com.vn.product.entites.Order;
import sang.uaa.com.vn.repository.OrderRepository;
import sang.uaa.com.vn.repository.ProductRepository;
import sang.uaa.com.vn.utils.QueryStrategy;

@Slf4j
@Service
public class OrderServiceImpl {
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderMapper orderMapper;
	
	public Page<OrderDto> getPagination(RequestPagingBuilder<OrderDto> payload) {
		
		String field = payload.getFieldsOrderBy();
		int page = payload.getPage();
		int size = payload.getSize();
		Sort.Direction sortDirection;
		sortDirection = Direction.ASC;
		if (StringUtils.isNotEmpty(payload.getSortBy().name())) {
			sortDirection = payload.getSortBy();
		}
		String name = "";
		Pageable pageable = PageRequest.of(page, size);
		List<Long> products = new ArrayList<>();
		String productId = "";
		if (StringUtils.isNotEmpty(payload.getFilters().getProductName())) {
			List<Long> ids = this.productRepository.findIdLikeName(payload.getFilters().getProductName());
			products.addAll(ids);
			if (CollectionUtils.isNotEmpty(products)) {
				productId = String.valueOf(products.get(0));
			}
		}
		
		if (StringUtils.isNotBlank(payload.getFilters().getName())) {
			name = payload.getFilters().getName();
		}
		Page<Order> orderPageable = this.orderRepository.getPagination(name, productId, products, pageable);
		List<Order> orders = new ArrayList<>(orderPageable.getContent());

		QueryStrategy<Order> queryStrategy = new QueryStrategy<>();
		queryStrategy.setData(orders);
		queryStrategy.sort(field, payload.getSortBy().name());
		pageable = PageRequest.of(page, size, Sort.by(sortDirection, field));
		orderPageable = new PageImpl<>(orders, pageable, orders.size());
		Page<OrderDto> orderPage = orderPageable.map(order -> orderMapper.toDto(order));
		log.debug("Product Order {}", orderPage);
		return orderPage;
	}
}
