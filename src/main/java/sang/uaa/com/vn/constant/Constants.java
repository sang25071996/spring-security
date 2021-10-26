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
		
		/**
		 * 
		 * ApiURL
		 * Jan 3, 2021
		 * @author macbook
		 */
		private ApiURL() {
			
		}
		
		public static final String API_ROLE = "roles";
		public static final String API_USER = "users";
		public static final String API_PRIVILEGE = "privileges";
		public static final String API_PRODUCT_CATEGRORIES = "product-categrories";
		public static final String API_PRODUCT = "product";
		public static final String API_POST = "post";
		public static final String API_CATEGRORY = "categrory";
		public static final String API_NOTIFICATION = "notification";
		
	}

	public static final String BLANK = "";
	public static final String SUCCESS = "Success";
	public static final String UNAUTHORIZED = "Unauthorized"; 
	public static final String ANONYMUS = "Anonymus"; 
	public static final long JWT_EXPERITION = 3600000;
	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String ROLE = "ROLE";
	public static final String SYSTEM = "macbook";
	public static final String SLASH = "/";
	public static final String ENCODING_UTF8 = "UTF-8";
	public static final String ROLE_STR = "Role";
	public static final String ID_STR = "Id";
	public static final String USER_STR = "User";
	public static final String UNDERLINE = "_";
	public static final String MEGA_BTYES = "MB";
	public static final String UPDATE = "UPDATE";
	public static final String CREATE = "CREATE";
	public static final String DELETE = "DELETE";
	
	/**
	 * Define Error Param
	 */
	
	public static final String ERROR_DATA_IN_USED = "data-in-use";
	public static final String ERROR_DATA_DUPLICATE = "data-duplicate";
	public static final String ERROR_DATA_NOT_FOUND = "data-not-found";
	public static final String ERROR_DATA_NULL = "data-null";
	public static final String ERROR_DATA_EMPTY = "data-empty";
	public static final String ERROR_DATA_IS_EXIST = "data-is-exist";
	public static final String ERROR_DATA_IS_NOT_EXIST = "data-is-not-exist";
}
