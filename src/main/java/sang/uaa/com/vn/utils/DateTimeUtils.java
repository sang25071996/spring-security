package sang.uaa.com.vn.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sang.uaa.com.vn.utils.exception.NotFoundException;


/**
 * Date Utils
 * @author macbook
 * Nov 13, 2020
 */
public class DateTimeUtils {
	
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
	 * Nov 14, 2020
	 *-------------------
	 * @author macbook
	 * @param date
	 * @param format
	 * @return String date
	 */
	public static String dateFormat(Date date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		String dateStr = simpleDateFormat.format(date);
		return dateStr;
	}
}
