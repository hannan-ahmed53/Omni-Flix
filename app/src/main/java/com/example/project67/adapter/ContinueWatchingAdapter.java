package com.example.project67.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project67.R;
import com.example.project67.manager.MovieClickManager;
import com.example.project67.model.Movie;

import java.util.List;

public class ContinueWatchingAdapter extends RecyclerView.Adapter<ContinueWatchingAdapter.ContinueWatchingViewHolder> {

    private final Context context;
    private List<Movie> movieList;
    private final OnMovieClickListener onMovieClickListener;
    private final MovieClickManager movieClickManager;

    public interface OnMovieClickListener {
        void onMovieClick(Movie movie);
    }

    public ContinueWatchingAdapter(Context context, List<Movie> movieList, OnMovieClickListener listener, MovieClickManager clickManager) {
        this.context = context;
        this.movieList = new java.util.ArrayList<>(movieList); // Create a mutable copy
        this.onMovieClickListener = listener;
        this.movieClickManager = clickManager;
    }

    @NonNull
    @Override
    public ContinueWatchingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_continue_watching, parent, false);
        return new ContinueWatchingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContinueWatchingViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.bind(movie, onMovieClickListener, movieClickManager, this, position);
    }

    @Override
    public int getItemCount() {
        return movieList != null ? movieList.size() : 0;
    }
    
    public void updateList(List<Movie> newList) {
        this.movieList = newList;
        notifyDataSetChanged();
    }

    static class ContinueWatchingViewHolder extends RecyclerView.ViewHolder {

        ImageView moviePoster;
        TextView movieTitle;
        ImageButton removeButton;

        public ContinueWatchingViewHolder(@NonNull View itemView) {
            super(itemView);
            moviePoster = itemView.findViewById(R.id.movie_poster);
            movieTitle = itemView.findViewById(R.id.movie_title);
            removeButton = itemView.findViewById(R.id.remove_button);
        }

        public void bind(final Movie movie, final OnMovieClickListener listener, final MovieClickManager clickManager, 
                        final ContinueWatchingAdapter adapter, final int position) {
            movieTitle.setText(movie.getTitle());

            if (movie.getPosterPath() != null && !movie.getPosterPath().isEmpty()) {
                Glide.with(itemView.getContext())
                        .load(movie.getPosterPath())
                        .into(moviePoster);
            } else if (movie.getPosterResource() != 0) {
                Glide.with(itemView.getContext())
                        .load(movie.getPosterResource())
                        .into(moviePoster);
            }

            itemView.setOnClickListener(v -> listener.onMovieClick(movie));

            removeButton.setOnClickListener(v -> {
                clickManager.removeFromContinueWatching(movie.getTitle());
                // Remove from local list and notify
                adapter.movieList.remove(position);
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position, adapter.movieList.size());
            });
        }
    }
}

