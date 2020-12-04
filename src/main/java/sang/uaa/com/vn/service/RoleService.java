package sang.uaa.com.vn.service;

import java.util.List;

import sang.uaa.com.vn.dto.RoleDto;

public interface RoleService {
	RoleDto save(RoleDto roleDto);

	RoleDto getRoleById(Long id);

	RoleDto edit(RoleDto roleDto);
	
	List<RoleDto> findAll();
}
