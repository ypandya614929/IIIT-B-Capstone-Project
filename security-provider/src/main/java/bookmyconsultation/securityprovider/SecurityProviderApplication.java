package bookmyconsultation.securityprovider;

import bookmyconsultation.securityprovider.entity.User;
import bookmyconsultation.securityprovider.repository.UserRepo;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class SecurityProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityProviderApplication.class, args);
	}

	@Bean
	ApplicationRunner init(UserRepo userRepo) {
		return (ApplicationArguments args) -> initialSetup(userRepo);
	}

	private void initialSetup(UserRepo userRepo) {
		User user = new User("normal-user@abc.com", "$2y$12$LQOfAEPJcdbE0Ko5JBETnOsI1O7fLYYqB5XnASfjZTLYRR2FIZQ8S", "ROLE_USER");
		User admin = new User("admin-user@abc.com", "$2y$12$23JC573SCMFvChDCo5O/L.scweeGWDV0ikQ.YpfF8wrDA8d1ojAgG", "ROLE_ADMIN");

		userRepo.save(user);
		userRepo.save(admin);
	}

}
