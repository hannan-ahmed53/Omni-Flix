package com.example.project67.data;

import com.example.project67.repository.MovieRepository;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseInitializer {

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void populateAsync(final AppDatabase db) {
        executor.execute(() -> {
            // Check if the database is empty before populating
            if (db.movieDao().getAllMovies().isEmpty()) {
                db.movieDao().insertAll(MovieRepository.getAllMovies());
            }
        });
    }
}
