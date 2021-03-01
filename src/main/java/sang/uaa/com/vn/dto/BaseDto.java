package sang.uaa.com.vn.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDto implements Serializable {
	
	private String createdBy;

	private Date createdDateTime;

	private Date updatedDateTime;

	private String updatetedBy;
}
