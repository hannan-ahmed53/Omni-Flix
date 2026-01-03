package com.example.project67;

import android.app.Application;
import androidx.appcompat.app.AppCompatDelegate;
import com.example.project67.data.AppDatabase;
import com.example.project67.data.DatabaseInitializer;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Set default theme to Light Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        // Initialize the database when the application starts
        AppDatabase db = AppDatabase.getDatabase(this);
        DatabaseInitializer.populateAsync(db);
    }
}
