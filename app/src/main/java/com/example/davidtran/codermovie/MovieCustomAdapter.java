package com.example.davidtran.codermovie;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by davidtran on 6/15/17.
 */

public class MovieCustomAdapter extends ArrayAdapter<Movie>
{
public List<Movie> movieList;
    boolean isLandscape;
    

    public MovieCustomAdapter(@NonNull Context context, List<Movie> movieList) {
        super(context,-1);
        this.movieList = movieList;
        isLandscape = getContext().getResources().getBoolean(R.bool.isLandscape);

    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Movie getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Movie movie = movieList.get(position);
        ViewHolder viewHolder;

        if(convertView != null)
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        else{
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.title.setText(movie.getTitle());

        viewHolder.overview.setText(movie.getOverview());

        loadImage(movie,viewHolder,isLandscape);
        Log.d("My log: ",movie.getPosterPath());
        return convertView;
    }
    private void loadImage(Movie movie,ViewHolder viewHolder,boolean isLandscape){
        if(!isLandscape) {
            Glide.with(getContext())
                    .load(movie.getPosterPath())
                    .into(viewHolder.poster);
        }
        else{
            Glide.with(getContext())
                    .load(movie.getBackdropPath())
                    .into(viewHolder.poster);
        }
    }
    static class ViewHolder {
        @BindView(R.id.tv_title) TextView title;
        @BindView(R.id.tv_overview) TextView overview;
        @BindView(R.id.iv_poster) ImageView poster;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
