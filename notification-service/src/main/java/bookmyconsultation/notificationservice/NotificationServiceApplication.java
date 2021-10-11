package bookmyconsultation.notificationservice;

import bookmyconsultation.notificationservice.service.Consumer;
import bookmyconsultation.notificationservice.service.NotificationService;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@Bean
	public Consumer consumer(){return new Consumer();}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ObjectMetadata metadata() {
		return new ObjectMetadata();
	}

	@Bean
	public NotificationService notificationService() {
		return new NotificationService();
	}

}
