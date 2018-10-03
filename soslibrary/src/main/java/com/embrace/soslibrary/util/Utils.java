package com.embrace.soslibrary.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.inputmethod.InputMethodManager;

import java.util.List;

public class Utils {

    private Utils() {}

    public static void hideKeyboard(@Nullable Context context) {
        try {
            if (context != null) {
                Activity a = (Activity) context;
                InputMethodManager imm = (InputMethodManager) a.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(a.getCurrentFocus().getWindowToken(), 0);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static boolean isAppIsInBackground(final Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
            if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                for (String activeProcess : processInfo.pkgList) {
                    if (activeProcess.equals(context.getPackageName())) {
                        isInBackground = false;
                    }
                }
            }
        }
        return isInBackground;
    }
}
