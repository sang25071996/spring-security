package sang.uaa.com.vn.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(content = Include.NON_NULL)
@Getter
@Setter
public class RoleDto extends BaseDto implements Serializable {
	
	private Long id;
	@NotBlank(message = "role not empty")
	private String name;
	
}
