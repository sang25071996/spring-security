package sang.uaa.com.vn.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import sang.uaa.com.vn.dto.OrderDto;
import sang.uaa.com.vn.product.entites.Order;
import sang.uaa.com.vn.product.entites.Product;
import sang.uaa.com.vn.repository.ProductRepository;
import sang.uaa.com.vn.utils.ApplicationContexUtils;

@Mapper(componentModel = "spring")
public interface OrderMapper {
	
	@Mapping(source = "product" , target = "productName", qualifiedByName = "getProduct")
	OrderDto toDto(Order entity);
	
	Order toEntity(OrderDto dto);
	
	@Named("getProduct")
	default String getProduct(Long id) {
		ProductRepository productRepository = ApplicationContexUtils.getContext().getBean(ProductRepository.class);
		Product product = productRepository.getOne(id);
		return product.getName();
	}
}
