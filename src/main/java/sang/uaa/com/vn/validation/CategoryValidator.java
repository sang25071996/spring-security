package sang.uaa.com.vn.validation;

import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import sang.uaa.com.vn.common.dto.ErrorParam;
import sang.uaa.com.vn.common.dto.SysError;
import sang.uaa.com.vn.constant.Constants;
import sang.uaa.com.vn.dto.CategroryDto;
import sang.uaa.com.vn.exception.BadRequestException;

public class CategoryValidator implements ValidatorService {
	
	private static final String NAME = "name";
	private static final String ID = "id";
	
	@Override
	public void validate(String validator, Map<String, Object> map) {
		
		Object object = map.get(validator);
		switch (validator) {
			case Constants.CREATE:
				validateCreateRequest((CategroryDto) object);
				break;
			
			case Constants.UPDATE:
				validateUpdateRequest((CategroryDto) object);
				break;
			
			default:
				break;
		}
	}
	
	private void validateUpdateRequest(CategroryDto categroryDto) {
		if (StringUtils.isBlank(categroryDto.getName())) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_EMPTY, new ErrorParam(NAME)));
		}
		
	}
	
	private void validateCreateRequest(CategroryDto categroryDto) {
		if (ObjectUtils.isEmpty(categroryDto.getId())) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_EMPTY, new ErrorParam(ID)));
		}
		if (StringUtils.isBlank(categroryDto.getName())) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_EMPTY, new ErrorParam(NAME)));
		}
	}
	
}
