package sang.uaa.com.vn.utils;

import java.io.File;

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
}
