package com.example.project67.ui.trending;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project67.R;
import com.example.project67.adapter.MovieAdapter;
import com.example.project67.adapter.OnMovieClickListener;
import com.example.project67.manager.MovieClickManager;
import com.example.project67.model.Movie;
import com.example.project67.repository.MovieRepository;

public class TrendingFragment extends Fragment implements OnMovieClickListener {

    private MovieClickManager movieClickManager;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        movieClickManager = new MovieClickManager(context.getApplicationContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trending, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.trending_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        MovieAdapter adapter = new MovieAdapter(requireContext(), MovieRepository.getTrendingMovies(), this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onMovieClick(Movie movie) {
        movieClickManager.recordMovieClick(movie);
        if (movie.getYoutubeUrl() != null && !movie.getYoutubeUrl().isEmpty()) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(movie.getYoutubeUrl()));
            try {
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(requireContext(), "Could not open trailer!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(requireContext(), "No trailer available.", Toast.LENGTH_SHORT).show();
        }
    }
}
