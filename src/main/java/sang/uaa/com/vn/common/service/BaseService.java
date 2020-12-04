package sang.uaa.com.vn.common.service;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import sang.uaa.com.vn.entites.BaseEntity;
import sang.uaa.com.vn.utils.WebUtils;

public class BaseService {
	
	@Autowired
	protected DozerBeanMapper dozerBeanMapper;
	
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
}
