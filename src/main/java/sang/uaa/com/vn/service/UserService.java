package sang.uaa.com.vn.service;

import java.util.List;

import sang.uaa.com.vn.dto.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	
	List<UserDto> findAllUser();
}
