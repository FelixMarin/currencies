package virtualcave.springbootexercice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.filter.ForwardedHeaderFilter;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
@ComponentScan(basePackages = "virtualcave.springbootexercice1.exercise.*")
@EntityScan("virtualcave.springbootexercice1.exercise.entity")
@EnableJpaRepositories("virtualcave.springbootexercice1.exercise.repository")
public class SpringBootExercice1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExercice1Application.class, args);
	}

	@Bean
	ForwardedHeaderFilter forwardedHeaderFilter() {
		return new ForwardedHeaderFilter();
	}
}
