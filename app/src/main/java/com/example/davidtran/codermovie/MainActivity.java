package com.example.davidtran.codermovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_title) TextView title;
    @BindView(R.id.tv_overview) TextView overview;
    @BindView(R.id.iv_poster) ImageView poster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = RetrofitUtil.create();
        Movieapi movieapi = retrofit.create(Movieapi.class);
        movieapi.nowPlaying().enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                List<Movie> movies = response.body().getMovies();
                for (Movie m:
                     movies) {
                    Log.d("my log:",m.getTitle());
                }
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {

            }
        });
    }

}
