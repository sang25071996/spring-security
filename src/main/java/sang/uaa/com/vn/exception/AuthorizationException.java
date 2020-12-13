package sang.uaa.com.vn.exception;

import sang.uaa.com.vn.common.SysError;

/**
 * 
 * <p>AuthorizationException</p>
 * Nov 13, 2020
 *-------------------
 * @author macbook
 *
 */
public class AuthorizationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private SysError sysError;

	public AuthorizationException(SysError sysError) {
		this.sysError = sysError;
	}

	public AuthorizationException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public AuthorizationException(Throwable throwable) {
		super(throwable);
	}

	public AuthorizationException(String message) {
		super(message);
	}

	public SysError getSysError() {
		return sysError;
	}

	public void setSysError(SysError sysError) {
		this.sysError = sysError;
	}

}
