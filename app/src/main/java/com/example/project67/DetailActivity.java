package com.example.project67;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.project67.data.AppDatabase;
import com.example.project67.manager.MovieClickManager;
import com.example.project67.model.Movie;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    private AppDatabase db;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private MovieClickManager movieClickManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        db = AppDatabase.getDatabase(this);
        movieClickManager = new MovieClickManager(this);

        ImageView detailPoster = findViewById(R.id.detail_poster);
        TextView detailTitle = findViewById(R.id.detail_title);
        TextView detailOverview = findViewById(R.id.detail_overview);
        Button addToListButton = findViewById(R.id.add_to_list_button);
        Button playTrailerButton = findViewById(R.id.play_trailer_button);

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);

        if (movie != null) {
            detailTitle.setText(movie.getTitle());
            detailOverview.setText(movie.getOverview());

            if (movie.getPosterPath() != null) {
                Glide.with(this).load(movie.getPosterPath()).into(detailPoster);
            } else {
                Glide.with(this).load(movie.getPosterResource()).into(detailPoster);
            }

            updateMyListButton(addToListButton, movie);

            // Load current status from database
            executor.execute(() -> {
                List<Movie> myList = db.movieDao().getMyList();
                boolean isInList = false;
                for (Movie m : myList) {
                    if (m.getTitle().equals(movie.getTitle())) {
                        isInList = true;
                        break;
                    }
                }
                movie.isInMyList = isInList;
                runOnUiThread(() -> updateMyListButton(addToListButton, movie));
            });

            addToListButton.setOnClickListener(v -> {
                movie.isInMyList = !movie.isInMyList;
                executor.execute(() -> {
                    db.movieDao().setMyListStatus(movie.getTitle(), movie.isInMyList);
                    runOnUiThread(() -> {
                        updateMyListButton(addToListButton, movie);
                        String message = movie.isInMyList ? "Added to My List" : "Removed from My List";
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                    });
                });
            });

            playTrailerButton.setOnClickListener(v -> {
                // Add to continue watching when user clicks play trailer
                movieClickManager.addToContinueWatching(movie);
                
                if (movie.getYoutubeUrl() != null && !movie.getYoutubeUrl().isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(movie.getYoutubeUrl()));
                    try {
                        startActivity(intent);
                    } catch (Exception e) {
                        Toast.makeText(this, "Could not open trailer!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "No trailer available.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void updateMyListButton(Button button, Movie movie) {
        if (movie.isInMyList) {
            button.setText("Remove from My List");
        } else {
            button.setText("Add to My List");
        }
    }
}
