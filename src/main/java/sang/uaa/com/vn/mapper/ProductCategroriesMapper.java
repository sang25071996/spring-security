package sang.uaa.com.vn.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import sang.uaa.com.vn.dto.ProductCategroriesDto;
import sang.uaa.com.vn.product.entites.ProductCategrories;

@Mapper(componentModel = "spring")
public interface ProductCategroriesMapper {
	
	@Mapping(target = "products", ignore = true)
	ProductCategroriesDto toDto(ProductCategrories entity);
	
	ProductCategrories toEntity(ProductCategroriesDto dto);
}
