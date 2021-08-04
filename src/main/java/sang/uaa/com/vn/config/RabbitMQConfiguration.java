package sang.uaa.com.vn.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitMQConfiguration {
	
    private String username = "guest";
    private String password = "guest";
    
    private String exchangeType = "notify-socket.fanout";
    private String university = "notify.socket.university.queue";
    private String college = "notify.socket.college.queue";
    private String student = "notify.socket.student.queue";
    private final static String RABBIT_MQ_USERNAME = "sang1996";
    private final static String RABBIT_MQ_PASSWORD = "123456789@sang";
    private final static String ENDPOINT = "b-8aece28a-aafa-440f-bfa4-0eee047a6769.mq.ap-southeast-1.amazonaws.com";
    private String announcementQueue = "announcement-queue";
    private String announcementFanout = "announcement-fanout";
    
	@Bean
    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
//        cachingConnectionFactory.setUsername(username);
//        cachingConnectionFactory.setPassword(password);
//        cachingConnectionFactory.setPort(5672);
//        cachingConnectionFactory.setVirtualHost("/");
//        return cachingConnectionFactory;
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(ENDPOINT);
        connectionFactory.setPort(5671);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername(RABBIT_MQ_USERNAME);
        connectionFactory.setPassword(RABBIT_MQ_PASSWORD);
       
        return connectionFactory;
    }
	
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
//        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
    
    @Bean
    public Queue universityQueue() {
    	return new Queue(university, false);
    }
    
    @Bean
    public Queue studentQueue() {
    	return new Queue(student, false);
    }
    
    @Bean
    public Queue collegeQueue() {
    	return new Queue(college, false);
    }
    
    @Bean
    public FanoutExchange exchange() {
    	return new FanoutExchange(exchangeType);
    }
    
    @Bean
    public Binding universityBinding(Queue universityQueue, FanoutExchange exchange) {
    	return BindingBuilder.bind(universityQueue).to(exchange);
    }
    
    @Bean
    public Binding studentBinding(Queue studentQueue, FanoutExchange exchange) {
    	return BindingBuilder.bind(studentQueue).to(exchange);
    }
    
    @Bean
    public Binding collegeBinding(Queue collegeQueue, FanoutExchange exchange) {
    	return BindingBuilder.bind(collegeQueue).to(exchange);
    }
}
