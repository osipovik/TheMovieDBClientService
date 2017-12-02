package com.loyaltyplant.movie_info.config;

import com.loyaltyplant.movie_info.task.RatingCalculateTask;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan("com.loyaltyplant")
public class ApplicationConfig {

    @Getter
    private Map<Integer, RatingCalculateTask> calculateTaskMap = new HashMap<>();

    @Getter
    @Value("${common.api_service.api_key}")
    private String apiKey;

    @Getter
    @Value("${common.api_service.base_url}")
    private String baseUrl;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
        Resource[] resourceLocations = new Resource[]{
                new ClassPathResource("application.properties"),
                new FileSystemResource(System.getProperty("config.folder", "") + "/application.properties"),
        };
        p.setLocations(resourceLocations);
        p.setIgnoreResourceNotFound(true);
        p.setIgnoreUnresolvablePlaceholders(true);
        return p;
    }

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
