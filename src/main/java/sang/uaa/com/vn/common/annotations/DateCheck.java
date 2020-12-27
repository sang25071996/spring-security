package sang.uaa.com.vn.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import sang.uaa.com.vn.common.validator.DateCheckValidation;


@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Constraint(validatedBy = { DateCheckValidation.class })
public @interface DateCheck {
	
	String format() default "yyyy/MM/dd";
	
	String message() default "sorry date invalid";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {}; 
}
