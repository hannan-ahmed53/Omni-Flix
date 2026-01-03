package com.example.project67;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NewContentReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String movieTitle = intent.getStringExtra("movie_title");
        if (movieTitle != null) {
            NotificationHelper.showNewContentNotification(context, movieTitle);
        }
    }
}
