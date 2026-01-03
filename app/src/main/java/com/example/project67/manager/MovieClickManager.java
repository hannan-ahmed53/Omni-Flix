package com.example.project67.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.project67.model.Movie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieClickManager {

    private static final String PREFS_NAME = "MovieClicks";
    private static final String CLICKS_KEY = "clicks";
    private static final String LAST_WATCHED_KEY = "last_watched";
    private static final String CONTINUE_WATCHING_KEY = "continue_watching";
    private final SharedPreferences sharedPreferences;
    private final Gson gson = new Gson();

    public MovieClickManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void recordMovieClick(Movie movie) {
        Map<String, Integer> clicks = getClickMap();
        int count = clicks.getOrDefault(movie.getTitle(), 0);
        clicks.put(movie.getTitle(), count + 1);
        saveClickMap(clicks);
    }

    public List<Movie> getMostClickedMovies(List<Movie> allMovies) {
        Map<String, Integer> clicks = getClickMap();
        List<Movie> sortedMovies = new ArrayList<>(allMovies);

        Collections.sort(sortedMovies, (o1, o2) -> {
            int c1 = clicks.getOrDefault(o1.getTitle(), 0);
            int c2 = clicks.getOrDefault(o2.getTitle(), 0);
            return Integer.compare(c2, c1);
        });

        return sortedMovies;
    }

    private Map<String, Integer> getClickMap() {
        String json = sharedPreferences.getString(CLICKS_KEY, null);
        if (json == null) {
            return new HashMap<>();
        }
        Type type = new TypeToken<Map<String, Integer>>() {}.getType();
        return gson.fromJson(json, type);
    }

    private void saveClickMap(Map<String, Integer> map) {
        String json = gson.toJson(map);
        sharedPreferences.edit().putString(CLICKS_KEY, json).apply();
    }
    
    public void setLastWatchedMovie(Movie movie) {
        String json = gson.toJson(movie);
        sharedPreferences.edit().putString(LAST_WATCHED_KEY, json).apply();
    }
    
    public Movie getLastWatchedMovie() {
        String json = sharedPreferences.getString(LAST_WATCHED_KEY, null);
        if (json == null) {
            return null;
        }
        return gson.fromJson(json, Movie.class);
    }
    
    public void addToContinueWatching(Movie movie) {
        List<Movie> continueWatching = getContinueWatchingList();
        // Check if movie already exists
        boolean exists = false;
        for (Movie m : continueWatching) {
            if (m.getTitle().equals(movie.getTitle())) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            continueWatching.add(movie);
            saveContinueWatchingList(continueWatching);
        }
    }
    
    public void removeFromContinueWatching(String movieTitle) {
        List<Movie> continueWatching = getContinueWatchingList();
        continueWatching.removeIf(movie -> movie.getTitle().equals(movieTitle));
        saveContinueWatchingList(continueWatching);
    }
    
    public List<Movie> getContinueWatchingList() {
        String json = sharedPreferences.getString(CONTINUE_WATCHING_KEY, null);
        if (json == null) {
            return new ArrayList<>();
        }
        Type type = new TypeToken<List<Movie>>() {}.getType();
        return gson.fromJson(json, type);
    }
    
    private void saveContinueWatchingList(List<Movie> movies) {
        String json = gson.toJson(movies);
        sharedPreferences.edit().putString(CONTINUE_WATCHING_KEY, json).apply();
    }
}
