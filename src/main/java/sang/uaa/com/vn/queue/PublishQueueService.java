package sang.uaa.com.vn.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sang.uaa.com.vn.dto.MessageQueueDto;

@Service
public class PublishQueueService {
	
	private static final Logger LOG = LoggerFactory.getLogger(PublishQueueService.class);
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private FanoutExchange fanoutExchange;
	@Autowired
	private ObjectMapper mapper;
	
	public Boolean publishToUniversityQueue() {
		
		try {
			MessageQueueDto messageQueue = new MessageQueueDto();
			messageQueue.setId(2);
			messageQueue.setName("Quang Ngai");
			this.rabbitTemplate.setExchange(this.fanoutExchange.getName());
			this.rabbitTemplate.convertAndSend(this.mapper.writeValueAsString(messageQueue));
		} catch (AmqpException | JsonProcessingException e) {
			LOG.error("Publish To Queue Error: {}", e.getMessage());
			return false;
		}
		return true;
	}
}
