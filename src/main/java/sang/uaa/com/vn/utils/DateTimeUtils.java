package sang.uaa.com.vn.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sang.uaa.com.vn.exception.NotFoundException;


/**
 * Date Utils
 * @author macbook
 * Nov 13, 2020
 */
public class DateTimeUtils {
	
	private static Logger LOGGER = LoggerFactory.getLogger(DateTimeUtils.class);
	
	private DateTimeUtils() {
		
	}
	
	/**
	 * 
	 * <p>parse type string date from format to format date </p>
	 *  Example: YYYY/MM/DD to YYYY-MM-DD
	 * Nov 14, 2020
	 *-------------------
	 * @author macbook
	 * @param dateStr
	 * @param fromFormat
	 * @param toFormat
	 * @return String date
	 */
	public static String format(String dateStr, String fromFormat, String toFormat) {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fromFormat);
		Date date;
		try {
			date = simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			throw new NotFoundException("Parsed Date Error");
		}
		return dateFormat(date, toFormat);
	}
	
	/**
	 * 
	 * <p>parse date format to string</p>
	 * Example: new Date() -> YYYY/MM/DD
	 * <p>Nov 14, 2020</p>
	 *-------------------
	 * @author macbook
	 * @param date
	 * @param format
	 * @return String date
	 */
	public static String dateFormat(Date date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}
	
	/**
	 * 
	 * <p>is Date</p>
	 * <p>Dec 27, 2020</p>
	 * -------------------
	 * @author macbook
	 * @param date String
	 * @param format String
	 * @return boolean
	 */
	public static boolean isDate(String date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		boolean valid = false;
		try {
			simpleDateFormat.parse(date);
			// strict mode - check 30 or 31 days, leap year
			simpleDateFormat.setLenient(false);
			valid = true;
			
		} catch (ParseException e) {
			String message = MessageUtils.getMessage("MSG1", date);
			LOGGER.error(message);
			valid = false;
		}
		
		return valid;
	}
}
