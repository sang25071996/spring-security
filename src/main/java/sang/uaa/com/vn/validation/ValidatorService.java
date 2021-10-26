package sang.uaa.com.vn.validation;

import java.util.Map;

public interface ValidatorService {
	
	void validate(String validator, Map<String, Object> map);
}
