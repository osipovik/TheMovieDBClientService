package com.loyaltyplant.movie_info;

import com.loyaltyplant.movie_info.config.ApplicationConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MovieInfoMain {

    public static void main(String[] args) {
        new SpringApplicationBuilder(new Class<?>[] {ApplicationConfig.class})
                .web(true).run(args);
    }
}
