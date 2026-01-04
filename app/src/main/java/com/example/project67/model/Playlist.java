package com.example.project67.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "playlists")
public class Playlist {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "movie_titles")
    public String movieTitles; // Comma-separated movie titles

    // Default constructor for Room
    public Playlist() {
    }

    @Ignore
    public Playlist(String name) {
        this.name = name;
        this.movieTitles = "";
    }
}

