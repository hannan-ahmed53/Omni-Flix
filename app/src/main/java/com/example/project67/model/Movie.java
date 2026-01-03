package com.example.project67.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "movies")
public class Movie implements Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "poster_resource")
    public int posterResource;

    @ColumnInfo(name = "poster_path")
    public String posterPath;

    @ColumnInfo(name = "youtube_url")
    public String youtubeUrl;

    @ColumnInfo(name = "overview")
    public String overview;

    @ColumnInfo(name = "category")
    public String category;
    
    @ColumnInfo(name = "genre")
    public String genre;

    @ColumnInfo(name = "is_in_my_list")
    public boolean isInMyList;

    // Default constructor for Room
    public Movie() {
        this.title = ""; // Primary keys cannot be null
    }

    // Constructor for movies with a local drawable resource for the poster
    @Ignore
    public Movie(@NonNull String title, int posterResource, String youtubeUrl, String overview, String category) {
        this.title = title;
        this.posterResource = posterResource;
        this.youtubeUrl = youtubeUrl;
        this.overview = overview;
        this.category = category;
        this.genre = "";
        this.posterPath = null;
        this.isInMyList = false;
    }
    
    @Ignore
    public Movie(@NonNull String title, int posterResource, String youtubeUrl, String overview, String category, String genre) {
        this.title = title;
        this.posterResource = posterResource;
        this.youtubeUrl = youtubeUrl;
        this.overview = overview;
        this.category = category;
        this.genre = genre;
        this.posterPath = null;
        this.isInMyList = false;
    }

    // Constructor for movies with a remote URL for the poster
    @Ignore
    public Movie(@NonNull String title, String posterPath, String youtubeUrl, String overview, String category) {
        this.title = title;
        this.posterPath = posterPath;
        this.youtubeUrl = youtubeUrl;
        this.overview = overview;
        this.category = category;
        this.genre = "";
        this.posterResource = 0;
        this.isInMyList = false;
    }
    
    @Ignore
    public Movie(@NonNull String title, String posterPath, String youtubeUrl, String overview, String category, String genre) {
        this.title = title;
        this.posterPath = posterPath;
        this.youtubeUrl = youtubeUrl;
        this.overview = overview;
        this.category = category;
        this.genre = genre;
        this.posterResource = 0;
        this.isInMyList = false;
    }

    public String getTitle() {
        return title;
    }

    public int getPosterResource() {
        return posterResource;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public String getOverview() {
        return overview;
    }

    public String getCategory() {
        return category;
    }
}
