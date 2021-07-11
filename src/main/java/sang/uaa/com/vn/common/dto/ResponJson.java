package sang.uaa.com.vn.common.dto;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponJson<T> implements Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private String message;
	private transient T data;
	private SysError sysError;
	private HttpStatus status;

	public ResponJson() {

	}
	
	public ResponJson(String message, T data, SysError sysError, HttpStatus status) {
		super();
		this.message = message;
		this.data = data;
		this.sysError = sysError;
		this.status = status;
	}

	public ResponJson(String message, SysError sysError) {
		super();
		this.message = message;
		this.sysError = sysError;
	}

	public ResponJson(SysError sysError) {
		super();
		this.sysError = sysError;
	}
	
	public ResponJson(T data) {
		this.data = data;
	}

	public ResponJson(T data, HttpStatus status, String message) {
		this.data = data;
		this.status = status;
		this.message = message;
	}

	public ResponJson(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public SysError getSysError() {
		return sysError;
	}

	public void setSysError(SysError sysError) {
		this.sysError = sysError;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	

}
