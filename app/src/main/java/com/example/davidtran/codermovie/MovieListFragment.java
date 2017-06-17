package com.example.davidtran.codermovie;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by davidtran on 6/16/17.
 */

public class MovieListFragment extends Fragment {
    @BindView(R.id.lv_movie) ListView lvMovie;
    List<Movie> movieList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_movielist,container,false);
        ButterKnife.bind(this,view);
        getData();
        setupListener();
        return view;

    }
    private void setupListener(){
        lvMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openDetailMovie(movieList.get(position));
            }
        });
    }
    /*Get data from api*/
    private void getData(){
        Retrofit retrofit = RetrofitUtil.create();
        Movieapi movieapi = retrofit.create(Movieapi.class);
        movieapi.nowPlaying().enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                movieList = response.body().getMovies();
                lvMovie.setAdapter(new MovieCustomAdapter(getContext(),movieList));
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
    private void openDetailMovie(Movie movie){
        Bundle bundle = new Bundle();
        bundle.putParcelable("movie_item",movie);
        Fragment fragment = new MovieDetailFragment();
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container, fragment).commit();
    }

}
