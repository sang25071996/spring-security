//package sang.uaa.com.vn.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
//import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.sqs.AmazonSQSAsync;
//import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
//
//@Configuration
//public class SpringCloudSQSConfig {
//	@Value("${cloud.aws.region.static}")
//	private String region;
//
//	@Value("${cloud.aws.credentials.access-key}")
//	private String accessKey;
//
//	@Value("${cloud.aws.credentials.secret-key}")
//	private String secretKey;
//
//	@Bean
//	public QueueMessagingTemplate queueMessagingTemplate() {
//		return new QueueMessagingTemplate(amazonSQSAsync());
//	}
//
//	public AmazonSQSAsync amazonSQSAsync() {
//
//		return AmazonSQSAsyncClientBuilder.standard().withRegion(Regions.AP_SOUTHEAST_1)
//				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
//				.build();
//	}
//
//	@Bean
//	public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory() {
//		SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
//		factory.setAmazonSqs(amazonSQSAsync());
//		factory.setMaxNumberOfMessages(10);
//		return factory;
//	}
//
//}
