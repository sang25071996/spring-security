package sang.uaa.com.vn.mapper;

import org.mapstruct.Mapper;

import sang.uaa.com.vn.dto.RoleDto;
import sang.uaa.com.vn.user.entites.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
	
	RoleDto roleToRoleDto(Role role);
	
	Role roleDtoToRole(RoleDto roleDto);
}
