package sang.uaa.com.vn.common;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sang.uaa.com.vn.exception.NotFoundException;

/**
 * 
 * <p>
 * Message Service
 * </p>
 * Dec 26, 2020 -------------------
 * 
 * @author macbook
 */
public final class MessageService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);
	
	private static MessageService messageSevice = null;
	
	private ResourceBundle bundle;
	private MessageService () {
		
	}
	
	public synchronized void init(String propertiesName) {
		
		try {
			bundle = ResourceBundle.getBundle(propertiesName);
		} catch (Exception e) {
			String file = propertiesName + "properties";
			throw new NotFoundException(file, e);
		}
	}
	
	/**
	 * 
	 * <p>get Message</p>
	 * <p>Dec 26, 2020</p>
	 * -------------------
	 * @author macbook
	 * @param msgCode
	 * @param args
	 * @return message
	 */
	public String getMessage(String msgCode, String[] args) {
		
		if (bundle == null) {
			String className = getClass().getName();
			throw new NotFoundException(className);
		}
		String msg = msgCode;
		try {
			msg = bundle.getString(msgCode);
			if (ArrayUtils.isNotEmpty(args)) {
				MessageFormat messageFormat = new MessageFormat(msg);
				msg = messageFormat.format(args);
			}
		} catch (MissingResourceException e) {
			LOGGER.error(e.getMessage());
			return msg;
		}
		return msg;
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
	public String getMessage(String msgCode, String args) {
		
		if (bundle == null) {
			String className = getClass().getName();
			throw new NotFoundException(className);
		}
		String msg = msgCode;
		try {
			msg = bundle.getString(msgCode);
			if (StringUtils.isNotEmpty(args)) {
				MessageFormat messageFormat = new MessageFormat(msg);
				msg = messageFormat.format(args);
			}
		} catch (MissingResourceException e) {
			LOGGER.error(e.getMessage());
			return msg;
		}
		return msg;
	}
	
	/**
	 * 
	 * <p>
	 * get Instance
	 * </p>
	 * <p>
	 * Dec 26, 2020
	 * </p>
	 * -------------------
	 * 
	 * @author macbook
	 * @return messageSevice
	 */
	public static MessageService getInstance() {
		if (messageSevice == null) {
			messageSevice = new MessageService();
		}
		return messageSevice;
	}
}
