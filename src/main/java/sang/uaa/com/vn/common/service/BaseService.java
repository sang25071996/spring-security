package sang.uaa.com.vn.common.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;

import sang.uaa.com.vn.common.dto.ErrorParam;
import sang.uaa.com.vn.common.dto.SysError;
import sang.uaa.com.vn.constant.Constants;
import sang.uaa.com.vn.dto.BaseDto;
import sang.uaa.com.vn.entites.BaseEntity;
import sang.uaa.com.vn.exception.BadRequestException;
import sang.uaa.com.vn.exception.ServiceRunTimeException;
import sang.uaa.com.vn.utils.WebUtils;

public class BaseService {
	
	public int checkMaxPageSize(int pageSize) {
		return -1 == pageSize ? Integer.MAX_VALUE : pageSize;
	}
	
	protected String defaultIfNotBlank(String str, String defaultValue) {
		return StringUtils.isNotBlank(str) ? str : defaultValue;
	}
	
	protected <T> T defaultIfNotBlank(T object, T defaultValue) {
		return ObjectUtils.isNotEmpty(object) ? object : defaultValue;
	}
	
	protected <E extends BaseDto> void validatorObjectIsEmpty(E e, String errorKey) {
		if (ObjectUtils.isEmpty(e)) {
			throw new BadRequestException(new SysError(Constants.ERROR_DATA_EMPTY, new ErrorParam(errorKey)));
		}
	}
	
	/**
	 * 
	 * <p>Validator Object is empty</p>
	 * <p>Jul 10, 2021</p>
	 * -------------------
	 * @author macbook
	 * @param Map<Object, String> map, Object: value of field, String: error Key
	 */
	protected void validatorObjectIsEmpty(Map<Object, String> map) {
		
		for(Entry<Object, String> entry : map.entrySet()) {
			if (ObjectUtils.isEmpty(entry.getKey())) {
				throw new BadRequestException(new SysError(Constants.ERROR_DATA_EMPTY, new ErrorParam(entry.getValue())));
			}
		}
	}
	
	/**
	 * 
	 * <p>Validator field is blank</p>
	 * <p>Jul 10, 2021</p>
	 * -------------------
	 * @author macbook
	 * @param Map<Object, String> map, Object: value of field, String: error Key
	 */
	protected void validatorFieldIsBlank(Map<Object, String> map) {
		
		for(Entry<Object, String> entry : map.entrySet()) {
			validatorFieldIsBlank(entry.getKey(), entry.getValue());
		}
	}
	
	protected <E extends BaseDto> void validatorFieldIsBlank(Object field, String errorKey) {
		if (field instanceof String) {
			if (StringUtils.isBlank((String)field)) {
				throw new BadRequestException(new SysError(Constants.ERROR_DATA_EMPTY, new ErrorParam(errorKey)));
			}
		} else if (field instanceof Long) {
			if (ObjectUtils.isEmpty(field)) {
				throw new BadRequestException(new SysError(Constants.ERROR_DATA_EMPTY, new ErrorParam(errorKey)));
			}
		}
	}
	
	/**
	 * 
	 * <p>set update info</p>
	 * Nov 14, 2020
	 * -------------------
	 * @author macbook
	 * @param <E>
	 * @param e
	 */
	protected <E extends BaseEntity> void setUpdateInfo(E e) {
		String user = WebUtils.getPricipal();
		e.setUpdatetedBy(user);
	}
	
	/**
	 * 
	 * <p>set update info</p>
	 * Nov 14, 2020
	 *-------------------
	 * @author macbook
	 * @param <E>
	 * @param listE
	 */
	protected <E extends BaseEntity> void setUpdateInfo(List<E> listE) {
		for (E e : listE) {
			String user = WebUtils.getPricipal();
			e.setUpdatetedBy(user);
		}
	}
	
	/**
	 * 
	 * <p>Common set create info</p>
	 * Nov 14, 2020
	 *-------------------
	 * @author macbook
	 * @param <E>
	 * @param e
	 */
	protected <E extends BaseEntity> void setCreateInfo(E e) {
		String user = WebUtils.getPricipal();
		e.setCreatedBy(user);
	}
	
	/**
	 * 
	 * <p>Common set create info</p>
	 * Nov 14, 2020
	 *-------------------
	 * @author macbook
	 * @param List<E>
	 * @param listE
	 */
	protected <E extends BaseEntity> void setCreateInfo(List<E> listE) {
		for (E e : listE) {
			String user = WebUtils.getPricipal();
			e.setCreatedBy(user);
		}
	}
	
	/**
	 * 
	 * <p>get Instance</p>
	 * <p>Jan 7, 2021</p>
	 * -------------------
	 * @author macbook
	 * @param <T>
	 * @param clazz
	 * @return T
	 */
	public static <T> T getInstance(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new ServiceRunTimeException(e);
		}
	}
	
	public static <T> T getInstanceMappger(Class<T> clazz) {
		return Mappers.getMapper(clazz);
	}
}
