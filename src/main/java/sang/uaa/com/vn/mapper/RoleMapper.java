package sang.uaa.com.vn.mapper;

import org.mapstruct.Mapper;

import sang.uaa.com.vn.dto.RoleDto;
import sang.uaa.com.vn.entites.Role;

@Mapper
public interface RoleMapper {
	
	RoleDto roleToRoleDto(Role role);
	
	Role roleDtoToRole(RoleDto roleDto);
}
