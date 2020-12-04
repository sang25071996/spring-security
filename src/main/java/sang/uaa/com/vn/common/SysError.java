package sang.uaa.com.vn.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysError implements Serializable {
	private String code;
	private ErrorParam errorParam;

	public SysError(String code, ErrorParam errorParam) {
		super();
		this.code = code;
		this.errorParam = errorParam;
	}

	public SysError(ErrorParam errorParam) {
		super();
		this.errorParam = errorParam;
	}

	public SysError(String code) {
		super();
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ErrorParam getErrorParam() {
		return errorParam;
	}

	public void setErrorParam(ErrorParam errorParam) {
		this.errorParam = errorParam;
	}

}
