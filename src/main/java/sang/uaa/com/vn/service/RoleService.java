package sang.uaa.com.vn.service;

import java.util.List;

import org.springframework.data.domain.Page;

import sang.uaa.com.vn.common.dto.RequestPagingBuilder;
import sang.uaa.com.vn.dto.RoleDto;

public interface RoleService extends IBaseService<RoleDto> {
	
	List<RoleDto> getRoles();
	
	Page<RoleDto> filterPaging(RequestPagingBuilder<RoleDto> requestPagingBuilder);
}
