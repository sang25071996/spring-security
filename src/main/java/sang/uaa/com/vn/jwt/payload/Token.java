package sang.uaa.com.vn.jwt.payload;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 * <p>Token payload</p>
 * Nov 14, 2020
 *-------------------
 * @author macbook
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Token implements Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("token_type")
	private String tokenType = "Bearer";
	@JsonProperty("expire_time")
	private String expireTime;
}
