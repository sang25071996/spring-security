package sang.uaa.com.vn.common.controller;

import org.springframework.http.ResponseEntity;

import sang.uaa.com.vn.common.dto.ResponJson;

/**
 * <p>BaseController response Entity</p>
 * @author macbook
 * Nov 13, 2020
 */
public class BaseController {

	/**
	 * 
	 * <p>set value return in ResponseEntity</p>
	 * Nov 9, 2020
	 * @author macbook
	 * @param object
	 * @return ResponseEntity<ResponJson>
	 */
	public ResponseEntity<ResponJson> getResponseEntity(Object object) {
		ResponJson responJson = new ResponJson(object);
		return ResponseEntity.ok(responJson);
	}
}
