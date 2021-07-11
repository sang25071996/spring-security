package sang.uaa.com.vn.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import sang.uaa.com.vn.content.entites.Categrory;
import sang.uaa.com.vn.dto.CategroryDto;

@Mapper(componentModel = "spring")
public interface CategroryMapper {
	
	@Mapping(target = "posts", ignore = true)
	CategroryDto toDto(Categrory entity);
	
	Categrory toEntity(CategroryDto dto);
}
