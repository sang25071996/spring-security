package sang.uaa.com.vn.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;

import sang.uaa.com.vn.dto.PrivilegeDto;
import sang.uaa.com.vn.user.entites.Privilege;

@Mapper(componentModel = "spring")
public interface PrivilegeMapper {
	
	PrivilegeDto toDto(Privilege privilege);
	
	List<PrivilegeDto> toDto(Set<Privilege> set);
	
	Privilege toEntity(PrivilegeDto privilegeDto);
}
