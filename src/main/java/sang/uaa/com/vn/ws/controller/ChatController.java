package sang.uaa.com.vn.ws.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import sang.uaa.com.vn.dto.ChatMessage;

@Controller
public class ChatController {
	
	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		String time = new SimpleDateFormat("HH:mm").format(new Date());
	    return new ChatMessage(chatMessage.getFrom(), chatMessage.getText(), time);
	}
	
	@GetMapping("/chat")
	public String index() {
		return "chat";
	}
}
