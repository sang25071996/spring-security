package sang.uaa.com.vn.common.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sang.uaa.com.vn.common.dto.ResponJson;
import sang.uaa.com.vn.utils.WebUtils;

/**
 * <p>
 * BaseController response Entity
 * </p>
 * 
 * @author macbook Nov 13, 2020
 */
@Controller
public class BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
	
	/**
	 * 
	 * <p>
	 * set value return in ResponseEntity
	 * </p>
	 * Nov 9, 2020
	 * 
	 * @author macbook
	 * @param object
	 * @return ResponseEntity<ResponJson>
	 */
	public ResponseEntity<ResponJson> getResponseEntity(Object object) {
		ResponJson responJson = new ResponJson(object);
		return ResponseEntity.ok(responJson);
	}
	
	@GetMapping(path = "/dowload")
	public void dowloadFile(HttpServletResponse response, @RequestParam String fileName) {
	
		String filePath = "";
		try {
			filePath = ResourceUtils.getURL("classpath:file/demo.txt").getPath();
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage());
		}
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
}
