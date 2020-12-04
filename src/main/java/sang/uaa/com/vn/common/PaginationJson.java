package sang.uaa.com.vn.common;

import java.util.List;

/**
 * 
 * <p>PaginationJson</p>
 * Nov 14, 2020
 *-------------------
 * @author macbook
 * @param <E>
 */
public class PaginationJson<E> {
	
	public int totalRecord;
	public List<E> data;
	
	public PaginationJson(int totalRecord, List<E> data) {
		super();
		this.totalRecord = totalRecord;
		this.data = data;
	}
	
	public int getTotalRecord() {
		return totalRecord;
	}
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	
	public List<E> getData() {
		return data;
	}
	
	public void setData(List<E> data) {
		this.data = data;
	}
	
}
