package com.loyaltyplant.movie_info.model.response;

import com.loyaltyplant.movie_info.model.movie.MovieModel;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class MovieResponse extends MovieModel implements Serializable {
    private static final long serialVersionUID = -6490267767383536345L;
}
