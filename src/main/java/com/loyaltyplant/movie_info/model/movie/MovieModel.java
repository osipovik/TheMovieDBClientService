package com.loyaltyplant.movie_info.model.movie;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Getter @Setter
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieModel implements Serializable {
    private static final long serialVersionUID = -7458225786722682467L;

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
}
