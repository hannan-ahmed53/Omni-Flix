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

    // Default constructor for Room
    public Profile() {
    }

    // Constructor for creating new profiles
    @Ignore
    public Profile(String name) {
        this.name = name;
        this.profileImage = "guts"; // Default to Guts image
        this.password = "";
    }
    
    @Ignore
    public Profile(String name, String profileImage) {
        this.name = name;
        this.profileImage = profileImage;
        this.password = "";
    }
    
    @Ignore
    public Profile(String name, String profileImage, String password) {
        this.name = name;
        this.profileImage = profileImage;
        this.password = password;
    }
}
