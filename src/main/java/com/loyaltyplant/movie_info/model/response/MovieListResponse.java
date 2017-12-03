package com.loyaltyplant.movie_info.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@Getter @Setter
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement(name = "movieList")
public class MovieListResponse implements Serializable {
    private static final long serialVersionUID = -7416825925378729818L;

    private Integer page;
    @JsonProperty("total_results")
    private Integer totalResult;
    @JsonProperty("total_pages")
    private Integer totalPages;
    @JsonProperty("results")
    private List<Movie> movieList;

    @XmlElementWrapper(name = "results")
    @XmlElement(name = "movie")
    public List<Movie> getMovieList() {
        return movieList;
    }

    @Getter @Setter
    @EqualsAndHashCode
    @ToString
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Movie implements Serializable {
        private static final long serialVersionUID = -2340861871980417631L;

        @JsonProperty("vote_count")
        private Integer voteCount;
        private Integer id;
        private Boolean video;
        @JsonProperty("vote_average")
        private Double voteAverage;
        private String title;
        private Double popularity;
        @JsonProperty("poster_path")
        private String posterPath;
        @JsonProperty("original_language")
        private String originalLanguage;
        @JsonProperty("original_title")
        private String originalTitle;
        @JsonProperty("genre_ids")
        private List<Integer> genreIds = null;
        @JsonProperty("backdrop_path")
        private String backdropPath;
        private Boolean adult;
        private String overview;
        @JsonProperty("release_date")
        private String releaseDate;

        @XmlElementWrapper(name = "genreIds")
        @XmlElement(name = "genreId")
        public List<Integer> getGenreIds() {
            return genreIds;
        }
    }
}
