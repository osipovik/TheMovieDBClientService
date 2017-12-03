package com.loyaltyplant.movie_info.integration;

import com.loyaltyplant.movie_info.ApiUrl;
import com.loyaltyplant.movie_info.config.ApplicationConfig;
import com.loyaltyplant.movie_info.controller.MovieController;
import com.loyaltyplant.movie_info.controller.MovieControllerTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@WebAppConfiguration
public class AcceptTypeXmlIntegrationTest {
    private static final Logger LOG = LoggerFactory.getLogger(MovieControllerTest.class);

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void whenServletContext_thenItProvidesMovieController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(webApplicationContext.getBean(MovieController.class));
    }

    @Test
    public void getMovieById_XMLAccept_Test() throws Exception {
        mockMvc.perform(get(ApiUrl.MOVIE_INFO_GET_BY_ID, 346364).accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.xpath("/movie/id").string("346364"))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void getMovieList_XMLAccept_Test() throws Exception {
        mockMvc.perform(get(ApiUrl.MOVIE_INFO_GET_LIST).accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.xpath("/movieList/results/movie").nodeCount(20))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void getMovieList_XMLAccept_withQueryParams_Test() throws Exception {
        mockMvc.perform(
                    get(ApiUrl.MOVIE_INFO_GET_LIST)
                            .accept(MediaType.APPLICATION_XML)
                            .param("page", "5")
                            .param("primary_release_year", "1989")
                ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.xpath("/movieList/results/movie").nodeCount(20))
                .andExpect(MockMvcResultMatchers.xpath("/movieList/page").number(5D))
                .andExpect(MockMvcResultMatchers.xpath("/movieList/results/movie/releaseDate")
                        .string(startsWith("1989")))
                .andDo(print())
                .andReturn();
    }
}
