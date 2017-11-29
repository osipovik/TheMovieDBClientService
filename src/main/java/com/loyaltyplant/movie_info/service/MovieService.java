package com.loyaltyplant.movie_info.service;

import com.loyaltyplant.movie_info.config.ApplicationConfig;
import com.loyaltyplant.movie_info.model.response.MovieListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;

@Service
public class MovieService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieService.class.getSimpleName());

    @Autowired
    private ApplicationConfig applicationConfig;

    public MovieListResponse getMovieList(MultiValueMap params) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MovieListResponse> response = null;

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(applicationConfig.getBaseUrl());

        builder.path("/discover/movie");
        builder.queryParam("api_key", applicationConfig.getApiKey());
        builder.queryParams(params);

        try {
            response = restTemplate.getForEntity(builder.toUriString(), MovieListResponse.class);
            return response.getBody();
        } catch (HttpServerErrorException | HttpClientErrorException | UnknownHttpStatusCodeException e) {
            LOGGER.error("send request error. Msg: {}, responseHeaders: {}, responseBody: {}",
                    e.getMessage(), e.getResponseHeaders(), e.getResponseBodyAsString());
            LOGGER.error("Error", e);
        } catch (Exception e) {
            LOGGER.error("Unexpected error", e);
        }

        return null;
    }
}
