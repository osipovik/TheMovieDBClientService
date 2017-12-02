package com.loyaltyplant.movie_info.service.sender;

import com.loyaltyplant.movie_info.config.ApplicationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RequestSenderService<T> {

    private static final Logger LOG = LoggerFactory.getLogger(RequestSenderService.class.getSimpleName());

    @Autowired
    private ApplicationConfig applicationConfig;

    public ResponseEntity<T> sendRequest() {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(applicationConfig.getBaseUrl());

        return null;
    }
}
