package sang.uaa.com.vn.common.service;

import java.util.List;

import org.mapstruct.factory.Mappers;

import sang.uaa.com.vn.entites.BaseEntity;
import sang.uaa.com.vn.exception.ServiceRunTimeException;
import sang.uaa.com.vn.utils.WebUtils;

public class BaseService {
	
	public int checkMaxPageSize(int pageSize) {
		return -1 == pageSize ? Integer.MAX_VALUE : pageSize;
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
