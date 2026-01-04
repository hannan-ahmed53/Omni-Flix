package com.example.project67.manager;

import android.content.Context;
import android.content.SharedPreferences;

public class ProfileManager {
    private static final String PREFS_NAME = "profile_prefs";
    private static final String KEY_SELECTED_PROFILE_NAME = "selected_profile_name";
    private static final String KEY_SELECTED_PROFILE_ID = "selected_profile_id";
    
    private SharedPreferences prefs;
    
    public ProfileManager(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
    
    public void setSelectedProfile(String profileName, int profileId) {
        prefs.edit()
                .putString(KEY_SELECTED_PROFILE_NAME, profileName)
                .putInt(KEY_SELECTED_PROFILE_ID, profileId)
                .apply();
    }
    
    public String getSelectedProfileName() {
        return prefs.getString(KEY_SELECTED_PROFILE_NAME, null);
    }
    
    public int getSelectedProfileId() {
        return prefs.getInt(KEY_SELECTED_PROFILE_ID, -1);
    }
    
    public void clearSelectedProfile() {
        prefs.edit()
                .remove(KEY_SELECTED_PROFILE_NAME)
                .remove(KEY_SELECTED_PROFILE_ID)
                .apply();
    }
}

