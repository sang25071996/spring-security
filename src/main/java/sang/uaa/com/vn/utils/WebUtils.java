package sang.uaa.com.vn.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.security.core.context.SecurityContextHolder;

import sang.uaa.com.vn.constant.Constants;
import sang.uaa.com.vn.entites.Authorizer;

/**
 * 
 * <p>WebUtils</p>
 * Nov 14, 2020
 *-------------------
 * @author macbook
 */
public final class WebUtils {
	
	/**
	 *  WebUtils
	 * Nov 28, 2020
	 * @author macbook
	 */
	private WebUtils() {
		
	}
	public static String getPricipal() {
		String user = Constants.BLANK;
		if (SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {
			
			if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof Authorizer) {
				Authorizer authorizer = (Authorizer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				return authorizer.getUsername();
			} else {
				user  = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			}
		}
		
		return user;
	}
	
	/**
	 * 
	 * <p>decode url</p>
	 * Nov 14, 2020
	 *-------------------
	 * @author macbook
	 * @param url
	 * @return
	 */
	public static String decodeURL(String url) {
		try {
			return URLDecoder.decode(url, StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			return url;
		}
	}
	
	/**
	 * 
	 * <p>encode URL</p>
	 * Nov 14, 2020
	 *-------------------
	 * @author macbook
	 * @param url
	 * @return
	 */
	public static String encodeURL(String url) {
		try {
			return URLEncoder.encode(url, StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			return url;
		}
	}
}
