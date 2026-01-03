package com.example.project67.ui.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project67.DetailActivity;
import com.example.project67.R;
import com.example.project67.adapter.MovieAdapter;
import com.example.project67.adapter.OnMovieClickListener;
import com.example.project67.data.AppDatabase;
import com.example.project67.manager.MovieClickManager;
import com.example.project67.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearchFragment extends Fragment implements OnMovieClickListener {

    private AppDatabase db;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private MovieAdapter adapter;
    private List<Movie> allMovies;
    private final List<Movie> filteredList = new ArrayList<>();
    private MovieClickManager movieClickManager;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        db = AppDatabase.getDatabase(context.getApplicationContext());
        movieClickManager = new MovieClickManager(context.getApplicationContext());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        executor.execute(() -> {
            allMovies = db.movieDao().getAllMovies();
            filteredList.addAll(allMovies);
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.search_results_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        adapter = new MovieAdapter(requireContext(), filteredList, this);
        recyclerView.setAdapter(adapter);

        EditText searchBar = view.findViewById(R.id.search_bar);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return view;
    }

    private void filter(String text) {
        executor.execute(() -> {
            filteredList.clear();
            if (text.isEmpty()) {
                filteredList.addAll(allMovies);
            } else {
                String lowerText = text.toLowerCase();
                for (Movie movie : allMovies) {
                    // Search by title, genre, or category
                    if (movie.getTitle().toLowerCase().contains(lowerText) ||
                        (movie.genre != null && movie.genre.toLowerCase().contains(lowerText)) ||
                        (movie.getCategory() != null && movie.getCategory().toLowerCase().contains(lowerText))) {
                        filteredList.add(movie);
                    }
                }
            }
            requireActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());
        });
    }
    
    @Override
    public void onMovieClick(Movie movie) {
        movieClickManager.recordMovieClick(movie);
        movieClickManager.setLastWatchedMovie(movie); // Track last watched
        Intent intent = new Intent(requireContext(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        startActivity(intent);
    }
}
