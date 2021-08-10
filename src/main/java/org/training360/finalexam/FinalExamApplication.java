package org.training360.finalexam;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FinalExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalExamApplication.class, args);
    }

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }



    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper().findAndRegisterModules();
    }


}
