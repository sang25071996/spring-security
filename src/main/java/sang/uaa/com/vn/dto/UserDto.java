package sang.uaa.com.vn.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
public class UserDto extends BaseDto implements Serializable{

	private Long id;
	private String username;
	private String password;
	private Set<RoleDto> roles = new HashSet<>();
}
