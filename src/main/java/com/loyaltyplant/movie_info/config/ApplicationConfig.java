package com.loyaltyplant.movie_info.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

@Configuration
@ComponentScan("com.loyaltyplant.movie_info")
public class ApplicationConfig {

    @Getter
    @Value("${common.api_service.api_key}")
    private String apiKey;

    @Getter
    @Value("${common.api_service.base_url}")
    private String baseUrl;

    @Bean
    public Filter loggingFilter(){
        AbstractRequestLoggingFilter f = new AbstractRequestLoggingFilter() {

            @Override
            protected void beforeRequest(HttpServletRequest request, String message) {
                System.out.println("beforeRequest: " +message);
            }

            @Override
            protected void afterRequest(HttpServletRequest request, String message) {
                System.out.println("afterRequest: " +message);
            }
        };
        f.setIncludePayload(true);
        f.setIncludeQueryString(true);

        f.setBeforeMessagePrefix("BEFORE REQUEST  [");
        f.setAfterMessagePrefix("AFTER REQUEST    [");
        f.setAfterMessageSuffix("]\n");
        return f;
    }

}
