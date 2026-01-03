package com.example.project67.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.project67.model.Profile;
import java.util.List;

@Dao
public interface ProfileDao {

    @Insert
    void insert(Profile profile);
    
    @androidx.room.Delete
    void delete(Profile profile);

    @Query("SELECT * FROM profiles")
    List<Profile> getAllProfiles();
}
