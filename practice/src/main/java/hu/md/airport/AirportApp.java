package hu.progmasters.airport;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AirportApp {

    public static void main(String[] args) {
        SpringApplication.run(AirportApp.class);
    }

    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
