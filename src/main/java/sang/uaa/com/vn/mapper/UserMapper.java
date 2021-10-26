package sang.uaa.com.vn.mapper;

import org.mapstruct.Mapper;

import sang.uaa.com.vn.dto.UserDto;
import sang.uaa.com.vn.user.entites.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserDto userToUserDto(User user);
	
	User userDtoToUser(UserDto userDto);
}
