package sang.uaa.com.vn.service;

import java.util.List;

import sang.uaa.com.vn.dto.RoleDto;

public interface RoleService extends IBaseService<RoleDto> {
	
	List<RoleDto> getRoles();
}
