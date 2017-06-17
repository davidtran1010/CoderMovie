package com.example.davidtran.codermovie;

import com.example.davidtran.codermovie.model.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by davidtran on 6/15/17.
 */

public class NowPlaying {
    @SerializedName("results")
    private List<Movie> movies;

    public List<Movie> getMovies(){
        return movies;
    }

}
