package com.loyaltyplant.movie_info.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.loyaltyplant.movie_info.model.movie.MovieModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
@Getter @Setter
@EqualsAndHashCode
@ToString
public class MovieListResponse implements Serializable {
    private static final long serialVersionUID = -7416825925378729818L;

    private Integer page;
    @JsonProperty("total_results")
    private Integer totalResult;
    @JsonProperty("total_pages")
    private Integer totalPages;
    @JsonProperty("results")
    private List<MovieModel> movieModelList;
}
