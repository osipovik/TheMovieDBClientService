package com.loyaltyplant.movie_info.task;

import com.loyaltyplant.movie_info.TaskStatus;
import com.loyaltyplant.movie_info.model.response.MovieListResponse;
import com.loyaltyplant.movie_info.service.RatingCalculateService;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Getter
public class RatingCalculateTask implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(RatingCalculateService.class);

    @NonNull
    private Integer genreId;
    @NonNull
    private String baseUrl;
    @NonNull
    private String apiKey;
    @Setter
    private TaskStatus taskStatus = TaskStatus.CREATED;
    private Integer completedPercent = 0;
    private Double voteSum = 0d;
    private Double voteAverage = 0d;
    private LocalDateTime createdDateTime = LocalDateTime.now();
    private LocalDateTime lastStateUpdateDateTime = LocalDateTime.now();
    private LocalDateTime calculatedDateTime = LocalDateTime.now();

    @Override
    public void run() {
        taskStatus = TaskStatus.STARTED;
        lastStateUpdateDateTime = LocalDateTime.now();
        calculateRatingByGenreId();
    }

    private void calculateRatingByGenreId() {
        calculateRatingByGenreId(1);
    }

    private void calculateRatingByGenreId(Integer page) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MovieListResponse> responseEntity = null;

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl);
        builder.path("/genre/{genre_id}/movies");
        builder.queryParam("api_key", apiKey);
        builder.queryParam("page", page);
        builder.uriComponents(builder.buildAndExpand(genreId));
        LOG.info(builder.toUriString());

        try {
            //TODO нужен отдельный пул запросов, который будет конртолировать отправку не более 40 запросов за 10 секунд
            responseEntity = restTemplate.getForEntity(builder.toUriString(), MovieListResponse.class);
            MovieListResponse response = responseEntity.getBody();
            LOG.info(response.toString());

            if (response.getPage() < response.getTotalPages()) {
                int percent = response.getPage() * 100 / response.getTotalPages();
                LOG.info("percent = " + percent);
                this.completedPercent = percent;

                response.getMovieModelList().forEach(movie -> voteSum += movie.getVoteAverage());

                Thread.sleep(250); //maximum 40 requests every 10 seconds
                calculateRatingByGenreId(response.getPage()+1);
            } else {
                this.completedPercent = 100;
                this.voteAverage = this.voteSum / response.getTotalResult();
                this.taskStatus = TaskStatus.COMPLETED;
                this.calculatedDateTime = LocalDateTime.now();
            }

            lastStateUpdateDateTime = LocalDateTime.now();
        } catch (HttpServerErrorException | HttpClientErrorException | UnknownHttpStatusCodeException e) {
            LOG.error("send request error. Msg: {}, responseHeaders: {}, responseBody: {}",
                    e.getMessage(), e.getResponseHeaders(), e.getResponseBodyAsString());
            LOG.error("Error", e);
        } catch (Exception e) {
            LOG.error("Unexpected error", e);
        }
    }
}
