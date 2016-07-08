package com.example.test.baseforaproject.preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by test on 05-07-2016.
 */
public class AppPreference {
    private static final String APP_PREF = "rm_app_pref";
    private final Context mContext;
    private SharedPreferences mPreference;
    private SharedPreferences.Editor mEditor;

    private static String PREF_IS_LOGGED ="pref_is_logged";
    private static String PREF_LOGGED_IN_DATE ="pref_logged_in_date";
    private static String PREF_LOGGED_OUT_DATE ="pref_logged_out_date";
    public  AppPreference(Context context){
        this.mContext = context;
        mPreference = context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE);
        mEditor = mPreference.edit();
    }

    public void setLoggedInTime(long loggedInTime){
        mEditor.putLong(PREF_LOGGED_IN_DATE,loggedInTime);
        mEditor.commit();
    }

    public long getLoggedInTime(){
        return mPreference.getLong(PREF_LOGGED_IN_DATE,0L);
    }

    public void setLoggedOutTime(long loggedOutTime){
        mEditor.putLong(PREF_LOGGED_OUT_DATE,loggedOutTime);
        mEditor.commit();
    }

    public long getLoggedOutTime(){
        return mPreference.getLong(PREF_LOGGED_OUT_DATE,0L);
    }

    public void logout() {
        mEditor.clear();
        mEditor.commit();
    }
}

