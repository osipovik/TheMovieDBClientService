package com.loyaltyplant.movie_info.service;

import com.loyaltyplant.movie_info.config.ApplicationConfig;
import com.loyaltyplant.movie_info.model.response.RatingCalculateResponse;
import com.loyaltyplant.movie_info.task.RatingCalculateTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class RatingCalculateService {
    private static final Logger LOG = LoggerFactory.getLogger(RatingCalculateService.class);

    @Autowired
    private ApplicationConfig applicationConfig;
    private RatingCalculateTask calculateTask;

    public synchronized RatingCalculateResponse beginCalculate(Integer genreId) throws InterruptedException {
        if (applicationConfig.getCalculateTaskMap().containsKey(genreId)) {
            calculateTask = applicationConfig.getCalculateTaskMap().get(genreId);
        } else {
            calculateTask =
                    new RatingCalculateTask(genreId, applicationConfig.getBaseUrl(), applicationConfig.getApiKey());
            applicationConfig.getCalculateTaskMap().put(genreId, calculateTask);

            new Thread(calculateTask).start();
        }

        return new RatingCalculateResponse(calculateTask);
    }
}
