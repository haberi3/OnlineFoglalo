package HelixLab.Szoftverfejlesztes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
@Import(SpringDataRestConfiguration.class)
@SpringBootApplication
public class OnlineFoglaloApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineFoglaloApplication.class, args);
                System.out.println("ok");
	}

}
