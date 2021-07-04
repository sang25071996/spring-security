package sang.uaa.com.vn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ChatMessage {
	private String from;
	private String text;
	private String time;
}
