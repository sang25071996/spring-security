package sang.uaa.com.vn.exception;

/**
 * 
 * <p>
 * Service RunTime Exception
 * </p>
 * <p>Dec 27, 2020</p>
 * -------------------
 * 
 * @author macbook
 */
public class ServiceRunTimeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ServiceRunTimeException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public ServiceRunTimeException(Throwable throwable) {
		super(throwable);
	}
	
	public ServiceRunTimeException(String message) {
		super(message);
	}
}
