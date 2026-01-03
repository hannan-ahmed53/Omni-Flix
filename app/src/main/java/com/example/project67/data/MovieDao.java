package com.example.project67.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.project67.model.Movie;
import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Movie> movies);

    @Query("SELECT * FROM movies")
    List<Movie> getAllMovies();

    @Query("SELECT * FROM movies WHERE category = :category")
    List<Movie> getMoviesByCategory(String category);

    @Query("UPDATE movies SET is_in_my_list = :isInMyList WHERE title = :title")
    void setMyListStatus(String title, boolean isInMyList);

    @Query("SELECT * FROM movies WHERE is_in_my_list = 1")
    List<Movie> getMyList();
    
    @Query("SELECT * FROM movies WHERE genre LIKE '%' || :genre || '%'")
    List<Movie> getMoviesByGenre(String genre);
}
