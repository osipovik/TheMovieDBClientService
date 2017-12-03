package com.loyaltyplant.movie_info.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement(name = "movie")
public class MovieResponse implements Serializable {
    private static final long serialVersionUID = -6490267767383536345L;

    private Boolean adult;
    @JsonProperty("backdrop_path")
    private String backdropPath;
    @JsonProperty("belongs_to_collection")
    private Object belongsToCollection;
    private Integer budget;
    @JsonProperty("genres")
    private List<Genre> genreList = null;
    private String homepage;
    private Integer id;
    @JsonProperty("imdb_id")
    private String imdbId;
    @JsonProperty("original_language")
    private String originalLanguage;
    @JsonProperty("original_title")
    private String originalTitle;
    private String overview;
    private Double popularity;
    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("production_companies")
    private List<ProductionCompany> productionCompanyList = null;
    @JsonProperty("production_countries")
    private List<ProductionCountry> productionCountryList = null;
    @JsonProperty("release_date")
    private String releaseDate;
    private Integer revenue;
    private Integer runtime;
    @JsonProperty("spoken_languages")
    private List<SpokenLanguage> spokenLanguages = null;
    private String status;
    private String tagline;
    private String title;
    private Boolean video;
    @JsonProperty("vote_average")
    private Float voteAverage;
    @JsonProperty("vote_count")
    private Integer voteCount;

    @XmlElementWrapper(name = "genres")
    @XmlElement(name = "genre")
    public List<Genre> getGenreList() {
        return genreList;
    }

    @XmlElementWrapper(name = "productionCompanies")
    @XmlElement(name = "productionCompany")
    public List<ProductionCompany> getProductionCompanyList() {
        return productionCompanyList;
    }

    @XmlElementWrapper(name = "ProductionCountries")
    @XmlElement(name = "ProductionCountry")
    public List<ProductionCountry> getProductionCountryList() {
        return productionCountryList;
    }

    @XmlElementWrapper(name = "SpokenLanguages")
    @XmlElement(name = "SpokenLanguage")
    public List<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter @Setter
    @EqualsAndHashCode
    @ToString
    public static class Genre implements Serializable {
        private static final long serialVersionUID = 3313551637920306707L;

        private Integer id;
        private String name;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter @Setter
    @EqualsAndHashCode
    @ToString
    public static class ProductionCompany implements Serializable {
        private static final long serialVersionUID = -2493424247079285043L;

        private String name;
        private Integer id;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter @Setter
    @EqualsAndHashCode
    @ToString
    public static class ProductionCountry implements Serializable {
        private static final long serialVersionUID = -4552408641477424091L;

        @JsonProperty("iso_3166_1")
        private String iso;
        private String name;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter @Setter
    @EqualsAndHashCode
    @ToString
    public static class SpokenLanguage implements Serializable {
        private static final long serialVersionUID = 1471875246842028216L;

        @JsonProperty("iso_639_1")
        private String iso;
        private String name;
    }
}
