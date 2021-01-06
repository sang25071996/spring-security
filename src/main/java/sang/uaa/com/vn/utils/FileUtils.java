package sang.uaa.com.vn.utils;

import java.io.File;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import sang.uaa.com.vn.exception.NotFoundException;

/**
 * 
 * <p>File Utils</p>
 * Dec 27, 2020
 *-------------------
 * @author macbook
 */
public class FileUtils {
	
	/**
	 * FileUtils
	 * Dec 27, 2020
	 * @author macbook
	 */
	private FileUtils() {
		
	}
	
	/**
	 * 
	 * <p>Get file name</p>
	 * Nov 14, 2020
	 *-------------------
	 * @author macbook
	 * @param filePath String
	 * @return fileName
	 */
	public static String getFileName(String filePath) {
		if (StringUtils.isEmpty(filePath)) {
			throw new NotFoundException("File not found");
		}
		File file = new File(filePath);
		if (!file.exists()) {
			throw new NotFoundException("File is not exist");
		}
		return file.getName();
	}
	/**
	 * 
	 * <p>Get file name</p>
	 * <p>Jan 3, 2021</p>
	 * -------------------
	 * @author macbook
	 * @param file
	 * @return fileName
	 */
	public static String getFileName(File file) {
		if (ObjectUtils.isEmpty(file)) {
			throw new NotFoundException("File not found");
		}
		if (!file.exists()) {
			throw new NotFoundException("File is not exist");
		}
		return file.getName();
	}
}
