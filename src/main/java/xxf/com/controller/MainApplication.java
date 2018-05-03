package xxf.com.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import xxf.com.entities.UsersEntity;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAutoConfiguration
@ComponentScan("xxf.com")
@EnableCaching
public class MainApplication {

    @Bean
    public UsersEntity testS(){
        return new UsersEntity();
    }

    public static void main(String[] args) {

        SpringApplication.run(MainApplication.class,args);
    }
}
