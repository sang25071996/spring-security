package sang.uaa.com.vn.common;

/**
 * 
 * <p>Message Enum</p>
 * <p>Jan 3, 2021</p>
 *-------------------
 * @author macbook
 */
public enum MessageEnum {
	
	MSGCODE1("MSG_CODE1"),
	MSGCODE2("MSG_CODE2"),
	MSGCODE3("MSG_CODE3"),
	MSGCODE4("MSG_CODE4");
	
	private String value;
	
	/**
	 * 
	 * MessageEnum
	 * Jan 3, 2021
	 * @author macbook
	 * @param value
	 */
	private MessageEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	/**
	 * 
	 * <p>get Value</p>
	 * <p>Jan 3, 2021</p>
	 * -------------------
	 * @author macbook
	 * @param value String
	 * @return MessageEnum
	 */
	public static MessageEnum getValue(String value) {
		for (MessageEnum message : MessageEnum.values()) {
			if (message.getValue().equals(value)) {
				return message;
			}
		}
		return null;
	}
	
}
