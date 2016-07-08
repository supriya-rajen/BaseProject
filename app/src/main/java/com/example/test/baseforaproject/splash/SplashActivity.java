package com.example.test.baseforaproject.splash;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;

import com.example.test.baseforaproject.MainActivity;
import com.example.test.baseforaproject.R;
import com.example.test.baseforaproject.leftmenu.HomeActivity;
import com.example.test.baseforaproject.preference.AppPreference;

import java.lang.ref.WeakReference;

public class SplashActivity extends Activity {
    private static final long SPLASH_DURATION = 3000;
    private boolean isDestroyed = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        init();
        setupDefault();
        setupEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isDestroyed = true;
    }

    private void init() {

        RMHandler mHandler = new RMHandler(this);
        mHandler.sendEmptyMessageDelayed(1, SPLASH_DURATION);
    }

    private void setupDefault() {

    }

    private void setupEvent() {

    }
    private static class RMHandler extends Handler {
        WeakReference<SplashActivity> splash;
        RMHandler(SplashActivity splashScreen) {
            splash = new WeakReference<>(splashScreen);
        }

        @Override
        public void handleMessage(Message msg) {
            SplashActivity activity = splash.get();
            if (activity != null && msg.what == 1 && !activity.isDestroyed) {
                activity.launchNextScreen();
            }
        }
    }

    private void launchNextScreen() {
        Intent intent;
        AppPreference  appPreference = new AppPreference(SplashActivity.this);
        if((appPreference.getLoggedInTime() != 0L) && (appPreference.getLoggedOutTime() != 0L)){
            intent = new Intent(this, HomeActivity.class);
        }else{
            intent = new Intent(this, MainActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
