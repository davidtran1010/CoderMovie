package com.example.davidtran.codermovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.VideoView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by davidtran on 6/17/17.
 */

public class MovieDetailFragment extends Fragment {
    Movie movie;
    @BindView(R.id.vvDetail_Trailer) VideoView vvTrailer;
    @BindView(R.id.tvDetail_Title) TextView tvTitle;
    @BindView(R.id.tvDetail_Content) TextView tvOverview;
    @BindView(R.id.tvDetail_RDate) TextView tvRDate;
    @BindView(R.id.rbDetail_Rate) RatingBar rbRate;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_moviedetail,container,false);
        movie = getArguments().getParcelable("movie_item");
        ButterKnife.bind(this,view);
        bindDetailMovie();
        return view;
    }
    private void bindDetailMovie(){
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        tvRDate.setText(tvRDate.getText()+ " " + movie.getReleaseDate());
        Log.i("My log:","Vote average:" + movie.getVoteAverage());
        rbRate.setNumStars(7);
        rbRate.setRating(Float.valueOf(String.valueOf(movie.getVoteAverage())));

    }
}
