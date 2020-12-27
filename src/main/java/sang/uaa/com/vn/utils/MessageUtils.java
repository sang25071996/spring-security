package sang.uaa.com.vn.utils;

import sang.uaa.com.vn.common.MessageService;

/**
 * 
 * <p>Message Utils</p>
 * <p>Dec 27, 2020</p>
 *-------------------
 * @author macbook
 */
public class MessageUtils {
	
	/**
	 * MessageUtils
	 * Dec 27, 2020
	 * @author macbook
	 */
	private MessageUtils() {
		
	}
	
	/**
	 * 
	 * <p>get Message</p>
	 * <p>Dec 27, 2020</p>
	 * -------------------
	 * @author macbook
	 * @param msgCode String
	 * @param args String[]
	 * @return message
	 */
	public static String getMessage(String msgCode, String[] args) {
		MessageService messageService = MessageService.getInstance();
		return messageService.getMessage(msgCode, args);
	}
	
	/**
	 * 
	 * <p>get Message</p>
	 * <p>Dec 27, 2020</p>
	 * -------------------
	 * @author macbook
	 * @param msgCode String
	 * @param args String
	 * @return message
	 */
	public static String getMessage(String msgCode, String args) {
		MessageService messageService = MessageService.getInstance();
		return messageService.getMessage(msgCode, args);
	}
}
