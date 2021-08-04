//package sang.uaa.com.vn.queue;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
//import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
//import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
//import org.springframework.stereotype.Component;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import sang.uaa.com.vn.dto.UserDto;
//
//
//@Component
//public class SubcribeMessageAWS {
//	private static final Logger LOGGER = LoggerFactory.getLogger(SubcribeMessageAWS.class);
//	@SqsListener(value = "conganh", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
//    public void receiveMessage(String message, Acknowledgment acknowledgment) {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            UserDto userDto = mapper.readValue(message, UserDto.class);
//            LOGGER.info("user: {}", userDto);
//        } catch (Exception e) {
//        	LOGGER.error(e.getMessage());
//        }
//        acknowledgment.acknowledge();
//        LOGGER.debug("Message from SQS Queue {}", message);
//    }
//}
