package sang.uaa.com.vn.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserDto {

	private Long Id;
	private String username;
	private String password;
	private Set<RoleDto> roles = new HashSet<>();
}
