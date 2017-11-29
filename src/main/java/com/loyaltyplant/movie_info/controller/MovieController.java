package com.loyaltyplant.movie_info.controller;

import com.loyaltyplant.movie_info.ApiUrl;
import com.loyaltyplant.movie_info.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @ResponseBody
    @RequestMapping(value = ApiUrl.MOVIE_INFO_GET_LIST, method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Serializable getMovieList(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
        params.keySet().forEach(key -> paramMap.put(key, Arrays.asList(params.get(key))));
        return movieService.getMovieList(paramMap);
    }

}
