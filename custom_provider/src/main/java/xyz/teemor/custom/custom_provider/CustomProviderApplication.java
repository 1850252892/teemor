package xyz.teemor.custom.custom_provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("xyz.teemor.custom.custom_provider.dao")

public class CustomProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomProviderApplication.class, args);
    }
}
