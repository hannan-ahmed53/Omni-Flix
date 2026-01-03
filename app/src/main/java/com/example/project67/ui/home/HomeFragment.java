package com.example.project67.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project67.DetailActivity;
import com.example.project67.R;
import com.example.project67.adapter.ContinueWatchingAdapter;
import com.example.project67.adapter.MovieAdapter;
import com.example.project67.adapter.OnMovieClickListener;
import com.example.project67.data.AppDatabase;
import com.example.project67.manager.MovieClickManager;
import com.example.project67.model.Movie;
import com.example.project67.repository.MovieRepository;
import com.example.project67.util.NetworkUtils;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment implements OnMovieClickListener, ContinueWatchingAdapter.OnMovieClickListener {

    private AppDatabase db;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private MovieClickManager movieClickManager;
    private View view;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        db = AppDatabase.getDatabase(context.getApplicationContext());
        movieClickManager = new MovieClickManager(context.getApplicationContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        loadMoviesFromDb();

        if (NetworkUtils.isNetworkAvailable(requireContext())) {
            fetchMoviesFromRepository();
        }

        return view;
    }
    
    @Override
    public void onResume() {
        super.onResume();
        // Refresh continue watching when returning to fragment
        loadMoviesFromDb();
    }

    private void loadMoviesFromDb() {
        executor.execute(() -> {
            List<Movie> allMovies = db.movieDao().getAllMovies();
            
            // Continue Watching - get all movies being watched
            List<Movie> continueWatching = movieClickManager.getContinueWatchingList();
            
            // Trending - most clicked movies (top 10)
            List<Movie> trendingList = movieClickManager.getMostClickedMovies(allMovies);
            final List<Movie> trending = trendingList.size() > 10 ? 
                    new java.util.ArrayList<>(trendingList.subList(0, 10)) : trendingList;
            
            List<Movie> originals = db.movieDao().getMoviesByCategory("originals");
            List<Movie> bollywood = db.movieDao().getMoviesByCategory("bollywood");
            List<Movie> hollywood = db.movieDao().getMoviesByCategory("hollywood");
            List<Movie> korean = db.movieDao().getMoviesByCategory("korean");
            List<Movie> anime = db.movieDao().getMoviesByCategory("anime");

            requireActivity().runOnUiThread(() -> {
                setupContinueWatching(view, R.id.continue_watching_recycler_view, continueWatching);
                setupCategory(view, R.id.trending_recycler_view, trending);
                setupCategory(view, R.id.originals_recycler_view, originals);
                setupCategory(view, R.id.bollywood_recycler_view, bollywood);
                setupCategory(view, R.id.hollywood_recycler_view, hollywood);
                setupCategory(view, R.id.korean_recycler_view, korean);
                setupCategory(view, R.id.anime_recycler_view, anime);
            });
        });
    }

    private void fetchMoviesFromRepository() {
        executor.execute(() -> {
            db.movieDao().insertAll(MovieRepository.getAllMovies());
            // After fetching and updating the DB, reload the data to refresh the UI
            loadMoviesFromDb();
        });
    }

    private void setupContinueWatching(View rootView, int recyclerViewId, List<Movie> movieList) {
        RecyclerView recyclerView = rootView.findViewById(recyclerViewId);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
            ContinueWatchingAdapter adapter = new ContinueWatchingAdapter(requireContext(), movieList, this, movieClickManager);
            recyclerView.setAdapter(adapter);
        }
    }
    
    private void setupCategory(View rootView, int recyclerViewId, List<Movie> movieList) {
        RecyclerView recyclerView = rootView.findViewById(recyclerViewId);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
            MovieAdapter adapter = new MovieAdapter(requireContext(), movieList, this);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onMovieClick(Movie movie) {
        movieClickManager.recordMovieClick(movie);
        movieClickManager.addToContinueWatching(movie); // Add to continue watching
        Intent intent = new Intent(requireContext(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        startActivity(intent);
    }
}
