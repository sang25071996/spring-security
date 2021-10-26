package sang.uaa.com.vn.validation;

import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import sang.uaa.com.vn.common.dto.ErrorParam;
import sang.uaa.com.vn.common.dto.SysError;
import sang.uaa.com.vn.constant.Constants;
import sang.uaa.com.vn.dto.UserDto;
import sang.uaa.com.vn.exception.BadRequestException;
import sang.uaa.com.vn.user.entites.User;

public class UserValidator implements ValidatorService {
	
	private static final String PRIVILEGE_DTO = "PrivilegeDto";
	private static final String USER_NAME = "username";

	@Override
	public void validate(String validator, Map<String, Object> map) {
		
		Object object = map.get(validator);
		switch (validator) {
			case Constants.CREATE:
				validateCreateRequest((UserDto) object);
				break;
			
			case Constants.DELETE:
				validateDeleteRequest((User) object);
				break;
			
			default:
				break;
		}
	}
	
	public void validateCreateRequest(UserDto userDto) {
		if (ObjectUtils.isEmpty(userDto)) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_EMPTY, new ErrorParam(PRIVILEGE_DTO)));
		}
		
		if (StringUtils.isBlank(userDto.getUsername())) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_EMPTY, new ErrorParam(USER_NAME)));
		}
	}
	
	public void validateDeleteRequest(User user) {
		
		if (ObjectUtils.isEmpty(user)) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_IS_NOT_EXIST, new ErrorParam(USER_NAME)));
		}
	}
}
