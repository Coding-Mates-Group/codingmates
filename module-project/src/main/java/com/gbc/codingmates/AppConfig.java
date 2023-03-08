package com.gbc.codingmates;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    //converts dto to entity and vice versa
    @Bean
    public ModelMapper modelMapper(){

        return new ModelMapper();
    }
}
