package com.loyaltyplant.movie_info.controller;

import com.loyaltyplant.movie_info.ApiUrl;
import com.loyaltyplant.movie_info.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @ResponseBody
    @RequestMapping(value = ApiUrl.MOVIE_INFO_GET_LIST, method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Serializable getMovieList(HttpServletRequest request) {
        return movieService.getMovieList(request.getParameterMap());
    }

    @ResponseBody
    @RequestMapping(value = ApiUrl.MOVIE_INFO_GET_BY_ID, method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Serializable getMovieById(@PathVariable("movie_id") final int movieId) {
        return movieService.getMovieById(movieId);
    }

}
