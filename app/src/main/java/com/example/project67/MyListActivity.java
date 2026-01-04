package com.example.project67;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project67.adapter.MovieAdapter;
import com.example.project67.adapter.OnMovieClickListener;
import com.example.project67.data.AppDatabase;
import com.example.project67.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyListActivity extends AppCompatActivity implements OnMovieClickListener {

    private AppDatabase db;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private MovieAdapter adapter;
    private List<Movie> allMyListMovies;
    private List<Movie> filteredMovies;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        db = AppDatabase.getDatabase(this);

        RecyclerView recyclerView = findViewById(R.id.my_list_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        EditText searchEditText = findViewById(R.id.search_playlist);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterMovies(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        Button createPlaylistButton = findViewById(R.id.create_playlist_button);
        createPlaylistButton.setOnClickListener(v -> showCreatePlaylistDialog());

        loadMyList(recyclerView);
    }

    private void loadMyList(RecyclerView recyclerView) {
        executor.execute(() -> {
            allMyListMovies = db.movieDao().getMyList();
            filteredMovies = new ArrayList<>(allMyListMovies);
            runOnUiThread(() -> {
                if (allMyListMovies != null && !allMyListMovies.isEmpty()) {
                    adapter = new MovieAdapter(this, filteredMovies, this);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(this, "Your playlist is empty", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void filterMovies(String query) {
        if (allMyListMovies == null) return;
        
        filteredMovies.clear();
        if (query.isEmpty()) {
            filteredMovies.addAll(allMyListMovies);
        } else {
            String lowerQuery = query.toLowerCase();
            for (Movie movie : allMyListMovies) {
                if (movie.getTitle().toLowerCase().contains(lowerQuery) ||
                    (movie.genre != null && movie.genre.toLowerCase().contains(lowerQuery))) {
                    filteredMovies.add(movie);
                }
            }
        }
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    private void showCreatePlaylistDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Create New Playlist");

        final android.widget.EditText input = new android.widget.EditText(this);
        input.setHint("Playlist Name (e.g., Anime Playlist)");
        builder.setView(input);

        builder.setPositiveButton("Create", (dialog, which) -> {
            String playlistName = input.getText().toString().trim();
            if (!playlistName.isEmpty()) {
                Toast.makeText(this, "Playlist \"" + playlistName + "\" created! (Feature in development)", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please enter a playlist name", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload playlist when returning to this activity
        loadMyList(findViewById(R.id.my_list_recycler_view));
    }

    @Override
    public void onMovieClick(Movie movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        startActivity(intent);
    }
}
