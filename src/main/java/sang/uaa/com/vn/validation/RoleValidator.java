package sang.uaa.com.vn.validation;

import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import sang.uaa.com.vn.common.dto.ErrorParam;
import sang.uaa.com.vn.common.dto.SysError;
import sang.uaa.com.vn.constant.Constants;
import sang.uaa.com.vn.dto.RoleDto;
import sang.uaa.com.vn.exception.BadRequestException;
import sang.uaa.com.vn.exception.NotFoundException;
import sang.uaa.com.vn.user.entites.Role;

public class RoleValidator implements ValidatorService {
	
	private static final String NAME = "name";

	@Override
	public void validate(String validator, Map<String, Object> map) {
		Object object = map.get(validator);
		switch (validator) {
			case Constants.CREATE:
				validateCreateRequest((String) object);
				break;
			
			case Constants.UPDATE:
				validateUpdateRequest((RoleDto) object);
				break;
			case Constants.DELETE:
				validateDeleteRequest((Role) object);
				break;
			default:
				break;
		}
		
	}
	
	private void validateCreateRequest(String name) {
		if (StringUtils.isEmpty(name)) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_EMPTY, new ErrorParam(NAME)));
		}
		
	}
	
	private void validateUpdateRequest(RoleDto roleDto) {
		if (ObjectUtils.isEmpty(roleDto.getId())) {
			throw new NotFoundException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam(Constants.ID_STR)));
		}
		if (StringUtils.isEmpty(roleDto.getName())) {
			throw new NotFoundException(new SysError(Constants.ERROR_DATA_NULL, new ErrorParam(NAME)));
		}
	}
	
	private void validateDeleteRequest(Role role) {
		if (ObjectUtils.isEmpty(role)) {
			throw new NotFoundException(
					new SysError(Constants.ERROR_DATA_IS_NOT_EXIST, new ErrorParam(Constants.ID_STR)));
		}
		
	}
}
