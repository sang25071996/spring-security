package sang.uaa.com.vn.exception;

/**
 * 
 * <p>File Storage Exception</p>
 * <p>Jan 7, 2021</p>
 *-------------------
 * @author macbook
 */
public class FileStorageException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public FileStorageException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public FileStorageException(Throwable throwable) {
		super(throwable);
	}
	
	public FileStorageException(String message) {
		super(message);
	}
}
