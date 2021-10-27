package sang.uaa.com.vn.validation;

import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import sang.uaa.com.vn.common.dto.ErrorParam;
import sang.uaa.com.vn.common.dto.SysError;
import sang.uaa.com.vn.constant.Constants;
import sang.uaa.com.vn.dto.PostDto;
import sang.uaa.com.vn.exception.BadRequestException;

public class PostValidator implements ValidatorService {

	private static final String NAME = "name";
	private static final String ID = "id";
	@Override
	public void validate(String validator, Map<String, Object> map) {

		Object object = map.get(validator);
		switch (validator) {
			case Constants.CREATE:
				validateCreateRequest((PostDto) object);
				break;
			
			case Constants.UPDATE:
				validateUpdateRequest((PostDto) object);
				break;
			
			default:
				break;
		}
	}

	private void validateUpdateRequest(PostDto postDto) {
		if (StringUtils.isBlank(postDto.getName())) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_EMPTY, new ErrorParam(NAME)));
		}
		
		if (ObjectUtils.isEmpty(postDto.getCategroryDto().getId())) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_EMPTY, new ErrorParam(ID)));
		}
		
	}

	private void validateCreateRequest(PostDto postDto) {
		if (ObjectUtils.isEmpty(postDto.getId())) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_EMPTY, new ErrorParam(ID)));
		}
	}
	
}
