package sang.uaa.com.vn.service;

import java.util.List;

import sang.uaa.com.vn.dto.UserDto;

public interface UserService extends IBaseService<UserDto> {
	
	List<UserDto> getUsers();
}
