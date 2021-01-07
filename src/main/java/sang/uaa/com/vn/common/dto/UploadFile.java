package sang.uaa.com.vn.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * <p>Upload File</p>
 * <p>Jan 7, 2021</p>
 *-------------------
 * @author macbook
 */
@Getter
@Setter
public class UploadFile {
	
	private String userId;
	private String fileName;
	private String filePath;
	private String fileType;
	private long size;
}
