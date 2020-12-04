package sang.uaa.com.vn.service;

import java.util.List;

import sang.uaa.com.vn.dto.UserDto;
import sang.uaa.com.vn.utils.exception.NotFoundException;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	
	List<UserDto> findAllUser();
}
