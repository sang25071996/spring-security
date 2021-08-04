package sang.uaa.com.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sang.uaa.com.vn.common.controller.BaseController;
import sang.uaa.com.vn.common.dto.ResponJson;
import sang.uaa.com.vn.constant.Constants;
import sang.uaa.com.vn.queue.PublishQueueService;

@RestController
@RequestMapping(Constants.ApiURL.API_NOTIFICATION)
public class NotificationController extends BaseController {

	@Autowired
	private PublishQueueService publishQueueServiceImpl;

	@GetMapping()
	public ResponseEntity<ResponJson<Boolean>> publishToUniversityQueue() {
		return getResponseEntity(publishQueueServiceImpl.publishToUniversityQueue());
	}

//	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);
//
//	@Autowired
//	private QueueMessagingTemplate queueMessagingTemplate;
//
//	@Value("${cloud.aws.end-point.uri}")
//	private String endPoint;
//
//	@Autowired
//	private ObjectMapper objectMapper;
//
//	@PostMapping("/message")
//	public void sendMessage(@RequestBody UserDto userDto) {
//
//		try {
//
//			String jsonString = objectMapper.writeValueAsString(userDto);
//			queueMessagingTemplate.send(endPoint, MessageBuilder.withPayload(jsonString).build());
//			LOGGER.debug("Message sent successfully: {}", jsonString);
//		} catch (Exception e) {
//			LOGGER.error("Publish message error: {}", e.getMessage());
//		}
//	}
}
