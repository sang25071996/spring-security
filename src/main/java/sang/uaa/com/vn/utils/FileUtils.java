package sang.uaa.com.vn.utils;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

import sang.uaa.com.vn.utils.exception.NotFoundException;

public class FileUtils {
	
	private FileUtils() {
		
	}
	
	/**
	 * 
	 * <p>Get file name</p>
	 * Nov 14, 2020
	 *-------------------
	 * @author macbook
	 * @param filePath
	 * @return
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
