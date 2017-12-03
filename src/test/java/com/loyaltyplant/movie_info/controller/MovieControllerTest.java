package com.loyaltyplant.movie_info.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loyaltyplant.movie_info.ApiUrl;
import com.loyaltyplant.movie_info.model.response.MovieListResponse;
import com.loyaltyplant.movie_info.model.response.MovieResponse;
import com.loyaltyplant.movie_info.service.MovieService;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {
    private static final Logger LOG = LoggerFactory.getLogger(MovieControllerTest.class);

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MovieController movieController;
    @MockBean
    private MovieService movieService;

    @Test
    public void getMovieById_JSONAccept_Test() throws Exception {
        MovieResponse movie = (MovieResponse) getFileContentAsObject(
                "test_response/json/get_movie_by_id_response.json",
                MovieResponse.class);

        given(movieService.getMovieById(1)).willReturn(movie);

        MvcResult mvcResult =
                mockMvc.perform(get(ApiUrl.MOVIE_INFO_GET_BY_ID, 1).accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                        .andDo(print())
                        .andReturn();

        MovieResponse movieResponse = (MovieResponse)
                getObjectFromJsonString(mvcResult.getResponse().getContentAsString(), MovieResponse.class);

        assertEquals(movie, movieResponse);
    }

    @Test
    public void getMovieList_JSONAccept_Test() throws Exception {
        MovieListResponse movieList = (MovieListResponse) getFileContentAsObject(
                "test_response/json/get_movie_list_response.json",
                MovieListResponse.class);


        HttpServletRequest request = new MockHttpServletRequest();
        given(movieController.getMovieList(request)).willReturn(movieList);

        MvcResult mvcResult = mockMvc.perform(get(ApiUrl.MOVIE_INFO_GET_LIST).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andReturn();

        MovieListResponse movieListResponse = (MovieListResponse)
                getObjectFromJsonString(mvcResult.getResponse().getContentAsString(), MovieListResponse.class);

        assertEquals(movieList, movieListResponse);
    }

    @Test
    public void getMovieById_XMLAccept_Test() throws Exception {
        MovieResponse movie = (MovieResponse) getFileContentAsObject(
                "test_response/xml/get_movie_by_id_response.xml",
                MovieResponse.class);

        given(movieController.getMovieById(1)).willReturn(movie);

        MvcResult mvcResult =
                mockMvc.perform(get(ApiUrl.MOVIE_INFO_GET_BY_ID, 1).accept(MediaType.APPLICATION_XML))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_XML))
                        .andDo(print())
                        .andReturn();

        MovieResponse movieResponse = (MovieResponse)
                getObjectFromXmlString(mvcResult.getResponse().getContentAsString(), MovieResponse.class);

        assertEquals(movie, movieResponse);
    }

    @Test
    public void getMovieList_XMLAccept_Test() throws Exception {
        MovieListResponse movieList = (MovieListResponse) getFileContentAsObject(
                "test_response/xml/get_movie_list_response.xml",
                MovieListResponse.class);

        HttpServletRequest request = new MockHttpServletRequest();
        given(movieController.getMovieList(request)).willReturn(movieList);

        MvcResult mvcResult = mockMvc.perform(get(ApiUrl.MOVIE_INFO_GET_LIST).accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML))
                .andDo(print())
                .andReturn();

        MovieListResponse movieListResponse = (MovieListResponse)
                getObjectFromXmlString(mvcResult.getResponse().getContentAsString(), MovieListResponse.class);

        assertEquals(movieList, movieListResponse);
    }

    private Object getFileContentAsObject(String fileName, Class clazz) {
        Object result = null;
        ClassLoader classLoader = getClass().getClassLoader();

        try {
            String json = IOUtils.toString(
                    classLoader.getResourceAsStream(fileName));

            if (fileName.endsWith(".json")) {
                result = getObjectFromJsonString(json, clazz);
            } else if (fileName.endsWith(".xml")) {
                result = getObjectFromXmlString(json, clazz);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private Object getObjectFromJsonString(String str, Class clazz) {
        Object result = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            result = mapper.readValue(str, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private Object getObjectFromXmlString(String str, Class clazz) {
        Object result = null;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            result = jaxbUnmarshaller.unmarshal(IOUtils.toInputStream(str));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return result;
    }
}