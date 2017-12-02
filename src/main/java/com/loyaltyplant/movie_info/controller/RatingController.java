package com.loyaltyplant.movie_info.controller;

import com.loyaltyplant.movie_info.ApiUrl;
import com.loyaltyplant.movie_info.model.response.RatingCalculateResponse;
import com.loyaltyplant.movie_info.service.RatingCalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;

@Controller
public class RatingController {

    @Autowired
    private RatingCalculateService calculateService;

    @ResponseBody
    @RequestMapping(value = ApiUrl.CALCULATE_RATING_START_BY_GENRE_ID, method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Serializable calculateOverageVote(@RequestParam("genre_id") final int genreId) throws InterruptedException {
        RatingCalculateResponse response =  calculateService.beginCalculate(genreId);
        return response;
    }
}
