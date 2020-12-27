package sang.uaa.com.vn.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import sang.uaa.com.vn.common.annotations.DateCheck;
import sang.uaa.com.vn.utils.DateTimeUtils;

/**
 * <b>anotation DateCheck check validate date</b>
 * <p>Date Check Validation</p>
 * <p>Dec 27, 2020</p>
 *-------------------
 * @author macbook
 */
public class DateCheckValidation implements ConstraintValidator<DateCheck, String> {
	
	private String format;
	
	@Override
	public void initialize(DateCheck dateCheck) {
		this.format = dateCheck.format();
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		boolean valid = false;
		
		if (StringUtils.isNotBlank(value)) {
			return true;
		}
		
		if (DateTimeUtils.isDate(format, value)) {
			valid = true;
		}
		return valid;
	}
	
}
