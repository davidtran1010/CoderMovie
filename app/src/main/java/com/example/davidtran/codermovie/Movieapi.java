package com.example.davidtran.codermovie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by davidtran on 6/14/17.
 */

public interface Movieapi {
@GET("now_playing")
    Call<NowPlaying> nowPlaying();
}
