package sang.uaa.com.vn.common.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import sang.uaa.com.vn.common.dto.UploadFile;
import sang.uaa.com.vn.constant.Constants;
import sang.uaa.com.vn.entites.FileStorage;
import sang.uaa.com.vn.entites.User;
import sang.uaa.com.vn.exception.FileStorageException;
import sang.uaa.com.vn.repository.FileStorageRepository;
import sang.uaa.com.vn.repository.UserRepository;
import sang.uaa.com.vn.utils.FileUtils;
import sang.uaa.com.vn.utils.WebUtils;

/**
 * 
 * <p>File Storage Service</p>
 * <p>Jan 7, 2021</p>
 *-------------------
 * @author macbook
 */
@Component
public class FileStorageService extends BaseService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileStorageService.class);
	@Value("${file.upload.path}")
	private String uploadDir;
	@Autowired
	private Environment environment;
	@Autowired
	private ResourceLoader resourceLoader;
	private static final long BYTES = 1024;
	@Autowired
	private FileStorageRepository fileStorageRepository;
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 
	 * <p>dowload File</p>
	 * <p>Jan 10, 2021</p>
	 * -------------------
	 * @author macbook
	 * @param response
	 * @param fileName
	 */
	public void dowloadFile(HttpServletResponse response, String fileName) {
		
		FileStorage fileStorage = this.fileStorageRepository.findByFileName(fileName);
		if (ObjectUtils.isEmpty(fileStorage) || StringUtils.isEmpty(fileStorage.getFilePath())) {
			throw new FileStorageException("File name is null");
		}
		String filePath = fileStorage.getFilePath();
		LOGGER.info("File Path: {}", filePath);
		try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.setHeader("Content-disposition", "attachment; filename=" + WebUtils.encodeURL(filePath));
			response.setCharacterEncoding(StandardCharsets.UTF_8.name());
			IOUtils.copy(fileInputStream, response.getOutputStream());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * <p>upload File</p>
	 * <p>Jan 7, 2021</p>
	 * -------------------
	 * @author macbook
	 * @param multipartFile
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@Transactional(rollbackFor = Exception.class)
	public UploadFile uploadFile(MultipartFile multipartFile) throws IOException {
		
		if (ObjectUtils.isEmpty(multipartFile) || StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
			throw new FileStorageException("File is null.");
		}
		
		String originalFilename = FileUtils.cleanPath(multipartFile.getOriginalFilename());
		LOGGER.info("Load file name: {}", originalFilename);
		
		String fileSize = environment.getProperty("spring.servlet.multipart.max-file-size");
		LOGGER.info("Max size upload file: {}", fileSize);
		
		fileSize = StringUtils.replace(fileSize, Constants.MEGA_BTYES, "");
		long maxUploadSize = Long.parseLong(fileSize);
		maxUploadSize = maxUploadSize * (BYTES * BYTES);
		if (multipartFile.getSize() > maxUploadSize) {
			throw new FileStorageException("File size limit exceeded.");
		}
		
		LOGGER.info("Load file size: {}", multipartFile.getSize());
		Resource url = resourceLoader.getResource(uploadDir);
		File dir = new File(url.getURL().getPath());
		
		if (!dir.exists() && !dir.mkdirs()) {
			throw new FileStorageException("Fail of making directory");
		}
		
		if (StringUtils.isEmpty(originalFilename)) {
			throw new FileStorageException("File name is null.");
		}
		
		String fileName = new String(originalFilename.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8).replaceAll(" ", "_");
		String tempPath = dir + File.separator + fileName;
		Path path = Paths.get(tempPath);
		Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		LOGGER.info("Copy File to folder: {}", tempPath);
		
		FileStorage fileStorage;
		fileStorage = fileStorageRepository.findByFileName(fileName);
		UploadFile uploadFile = new UploadFile();
		uploadFile.setFileName(fileName);
		uploadFile.setFileType(multipartFile.getContentType());
		uploadFile.setSize(multipartFile.getSize());
		uploadFile.setFilePath(tempPath);
		uploadFile.setUserId(WebUtils.getPricipal());
		
		if (!ObjectUtils.isEmpty(fileStorage)) {
			uploadFile.setUserId(String.valueOf(fileStorage.getUserId().getId()));
			LOGGER.info("File name is exist in database");
			return uploadFile;
		}
		fileStorage = new FileStorage();
		setFileStorage(fileStorage, fileName, tempPath, multipartFile);
		return uploadFile;
	}
	
	/**
	 * 
	 * <p>set File Storage</p>
	 * <p>Jan 10, 2021</p>
	 * -------------------
	 * @author macbook
	 * @param fileStorage
	 * @param fileName
	 * @param tempPath
	 * @param multipartFile
	 */
	protected void setFileStorage(FileStorage fileStorage, String fileName, String tempPath, MultipartFile multipartFile) {
		
		fileStorage.setFileName(fileName);
		fileStorage.setFilePath(tempPath);
		fileStorage.setFileType(multipartFile.getContentType());
		User user = userRepository.findByUsername(WebUtils.getPricipal());
		fileStorage.setUserId(user);
		setCreateInfo(fileStorage);
		fileStorageRepository.save(fileStorage);
		LOGGER.info("Save File to database successful: {}", fileName);
	}
	
}
