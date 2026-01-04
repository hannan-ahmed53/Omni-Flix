package com.example.project67.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "profiles")
public class Profile {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    
    @ColumnInfo(name = "profile_image")
    public String profileImage;
    
    @ColumnInfo(name = "password")
    public String password;
    
    @ColumnInfo(name = "user_id")
    public String userId; // Firebase user ID to isolate profiles per user

    // Default constructor for Room
    public Profile() {
    }

    // Constructor for creating new profiles
    @Ignore
    public Profile(String name, String userId) {
        this.name = name;
        this.profileImage = "guts"; // Default to Guts image
        this.password = "";
        this.userId = userId;
    }
    
    @Ignore
    public Profile(String name, String profileImage, String userId) {
        this.name = name;
        this.profileImage = profileImage;
        this.password = "";
        this.userId = userId;
    }
    
    @Ignore
    public Profile(String name, String profileImage, String password, String userId) {
        this.name = name;
        this.profileImage = profileImage;
        this.password = password;
        this.userId = userId;
    }
}
