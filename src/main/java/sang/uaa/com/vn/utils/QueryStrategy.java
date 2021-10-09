package sang.uaa.com.vn.utils;

import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections4.comparators.ComparatorChain;

public class QueryStrategy<T> {
	
	private List<T> data;
	
	public void setData(List<T> data) {
		this.data = data;
	}
	
	public void sort(String field, String sortBy) {
		ComparatorChain<T> comparator = new ComparatorChain<>();
		if (sortBy.equals("ASC")) {
			comparator.addComparator(new BeanComparator<>(field));
			Collections.sort(data, comparator);
			return;
		}
		comparator.addComparator(new BeanComparator<>(field), true);
		Collections.sort(data, comparator);
	}
	
}
