package sang.uaa.com.vn.constant;

/**
 * 
 * <p>Constants Define</p>
 * Nov 14, 2020
 *-------------------
 * @author macbook
 */
public final class Constants {
	
	/**
	 * 
	 * <p>ApiURL</p>
	 * Nov 14, 2020
	 *-------------------
	 * @author macbook
	 */
	public final class ApiURL {
		
		private ApiURL() {
			
		}
		
		public static final String API_ROLE = "role";
	}

	public static final String BLANK = "";
	public static final String UNAUTHORIZED = "Unauthorized"; 
	public static final long JWT_EXPERITION = 3600000;
	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String ROLE = "ROLE";
	public static final String SYSTEM = "macbook";
	public static final String SLASH = "/";
	public static final String ENCODING_UTF8 = "UTF-8";
}
