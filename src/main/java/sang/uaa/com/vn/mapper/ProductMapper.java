package sang.uaa.com.vn.mapper;

import org.mapstruct.Mapper;

import sang.uaa.com.vn.dto.ProductDto;
import sang.uaa.com.vn.product.entites.Product;

@Mapper(componentModel = "spring", uses = ProductCategroriesMapper.class)
public interface ProductMapper {
	
	ProductDto toDto(Product entity);
	
	Product toEntity(ProductDto dto);
}
