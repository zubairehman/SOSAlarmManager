//package com.embrace.soslibrary.backgroundservices;
//
//import android.app.Service;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.PixelFormat;
//import android.os.IBinder;
//import android.os.PowerManager;
//import android.support.annotation.NonNull;
//import android.view.Gravity;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.LinearLayout;
//
//import com.embrace.soslibrary.R;
//import com.embrace.soslibrary.models.Constants;
//import com.embrace.soslibrary.util.Utils;
//
//import java.util.prefs.Preferences;
//
///**
// * Created by umair on 07/09/2017.
// */
//
//public class PowerButtonService extends Service {
//
//    private String TAG = "PowerButtonService";
//
//    public PowerButtonService() {
//
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//        if (Utils.isAppIsInBackground(getApplicationContext())) {
//            reopenActivity();
//        }
//
//        LinearLayout mLinear = new LinearLayout(getApplicationContext()) {
//
//            //home or recent button
//            public void onCloseSystemDialogs(String reason) {
//                if (Preferences.getInstance().getBoolean(Constants.IS_ALARM_ACTIVE, false)) {
//                    if ("globalactions".equals(reason)) {
//                        Intent closeDialog = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
//                        sendBroadcast(closeDialog);
//                    } else if ("homekey".equals(reason)) {
//                        reopenActivity();
//                    } else if ("recentapss".equals(reason)) {
//                        reopenActivity();
//                    } else if ("lock".equals(reason)) {
//                        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
//                        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "tag");
//                        wl.acquire();
//                    }
//                }
//            }
//
//            @Override
//            public boolean dispatchKeyEvent(@NonNull KeyEvent event) {
//                if (Preferences.getInstance().getBoolean(Constants.IS_ALARM_ACTIVE, false)) {
//                    if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
//                            || event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP
//                            || event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN
//                            || event.getKeyCode() == KeyEvent.KEYCODE_CAMERA
//                            || event.getKeyCode() == KeyEvent.KEYCODE_HOME
//                            || event.getKeyCode() == KeyEvent.KEYCODE_MOVE_HOME
//                            || event.getKeyCode() == KeyEvent.KEYCODE_AVR_POWER
//                            || event.getKeyCode() == KeyEvent.KEYCODE_STB_POWER
//                            || event.getKeyCode() == KeyEvent.KEYCODE_TV_POWER
//                            || event.getKeyCode() == KeyEvent.KEYCODE_POWER) {
//                    }
//                }
//                return super.dispatchKeyEvent(event);
//            }
//
//            @Override
//            public void onWindowFocusChanged(boolean hasFocus) {
//                super.onWindowFocusChanged(hasFocus);
//                if (Preferences.getInstance().getBoolean(Constants.IS_ALARM_ACTIVE, false)) {
//                    if (!hasFocus) {
//                        Intent closeDialog = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
//                        sendBroadcast(closeDialog);
//                    }
//                }
//            }
//        };
//
//        mLinear.setFocusable(true);
//
//        View mView = LayoutInflater.from(this).inflate(R.layout.layout_service_power_button, mLinear);
//        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
//
//        //params
//        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
//                100,
//                100,
//                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
//                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
//                        | WindowManager.LayoutParams.FLAG_FULLSCREEN
//                        | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
//                        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
//                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
//                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
//                        WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
//                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
//                        WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON,
//                PixelFormat.TRANSLUCENT);
//        params.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
//        wm.addView(mView, params);
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    private void reopenActivity() {
//        Intent myIntent = new Intent();
//        myIntent.setClassName(this, MainActivity.class.getName());
//        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
//                Intent.FLAG_ACTIVITY_SINGLE_TOP |
//                Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(myIntent);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//    }
//}