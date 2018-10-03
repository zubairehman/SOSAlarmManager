package com.embrace.soslibrary.ui.baseclasses.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class ActivityUtil {
    public static <T extends Activity> void launchActivity(T activity, Class<? extends AppCompatActivity> c) {
        Intent intent = new Intent(activity, c);
        activity.startActivity(intent);
    }

    public static <T extends Activity> void launchActivity(T activity, Class<? extends AppCompatActivity> c, ArrayList<Object> extrasMap) {
        Intent intent = new Intent(activity, c);
        intent.putExtra("extras", extrasMap);
        activity.startActivity(intent);
    }
}
