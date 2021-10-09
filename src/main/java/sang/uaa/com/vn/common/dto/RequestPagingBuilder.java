package sang.uaa.com.vn.common.dto;

import java.io.Serializable;

import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestPagingBuilder<T>  implements Serializable{
	
	private String fieldsOrderBy;
	private transient T filters;
	private Sort.Direction sortBy;
	private int page;
	private int size;
}
