package sang.uaa.com.vn.validation;

import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import sang.uaa.com.vn.common.dto.ErrorParam;
import sang.uaa.com.vn.common.dto.SysError;
import sang.uaa.com.vn.constant.Constants;
import sang.uaa.com.vn.dto.PrivilegeDto;
import sang.uaa.com.vn.exception.BadRequestException;

public class UserValidator implements ValidatorService {

	private static final String UPDATE = "UPDATE";
	
	private static final String CREATE = "CREATE";
	
	@Override
	public void validate(String validator, Map<String, Object> map) {
		
		Object object = map.get(validator);
		switch (validator) {
			case CREATE:
				validateCreateRequest((PrivilegeDto) object);
				break;
			
			case UPDATE:
				validateUpdateRequest((PrivilegeDto) object);
				break;
			
			default:
				break;
		}
	}
	
	public void validateCreateRequest(PrivilegeDto privilegeDto) {
		if (ObjectUtils.isEmpty(privilegeDto)) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_EMPTY, new ErrorParam("PrivilegeDto")));
		}
		
		if (StringUtils.isBlank(privilegeDto.getName())) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_EMPTY, new ErrorParam("name")));
		}
	}
	
	public void validateUpdateRequest(PrivilegeDto privilegeDto) {
		
		if (StringUtils.isBlank(privilegeDto.getName())) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_EMPTY, new ErrorParam("name")));
		}
	}
}
