package com.example.project67;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project67.adapter.MovieAdapter;
import com.example.project67.adapter.OnMovieClickListener;
import com.example.project67.data.AppDatabase;
import com.example.project67.model.Movie;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyListActivity extends AppCompatActivity implements OnMovieClickListener {

    private AppDatabase db;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private MovieAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        db = AppDatabase.getDatabase(this);

        RecyclerView recyclerView = findViewById(R.id.my_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        executor.execute(() -> {
            List<Movie> myList = db.movieDao().getMyList();
            runOnUiThread(() -> {
                adapter = new MovieAdapter(this, myList, this);
                recyclerView.setAdapter(adapter);
            });
        });
    }

    @Override
    public void onMovieClick(Movie movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        startActivity(intent);
    }
}
