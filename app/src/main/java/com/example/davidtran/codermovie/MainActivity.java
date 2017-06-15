package com.example.davidtran.codermovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lv_movie)
    ListView lvMovie;

    List<Movie> movieList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        getData();
    }


    private void getData(){
        Retrofit retrofit = RetrofitUtil.create();
        Movieapi movieapi = retrofit.create(Movieapi.class);
        movieapi.nowPlaying().enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                movieList = response.body().getMovies();
                lvMovie.setAdapter(new MovieCustomAdapter(MainActivity.this,movieList));
                for (Movie m: movieList) {

                    Log.d("my log:",m.getTitle());
                }
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }


}
