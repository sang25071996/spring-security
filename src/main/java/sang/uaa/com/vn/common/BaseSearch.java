package sang.uaa.com.vn.common;

import java.util.List;

/**
 * 
 * <p>BaseSearch</p>
 * Nov 14, 2020
 *-------------------
 * @author macbook
 */
public class BaseSearch<E> {
	
	private int totalRecord;
	private List<E> resultList;
	
	public BaseSearch(int totalRecord, List<E> resultList) {
		super();
		this.totalRecord = totalRecord;
		this.resultList = resultList;
	}
	
	public int getTotalRecord() {
		return totalRecord;
	}
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	
	public List<E> getResultList() {
		return resultList;
	}
	
	public void setResultList(List<E> resultList) {
		this.resultList = resultList;
	}
	
}
