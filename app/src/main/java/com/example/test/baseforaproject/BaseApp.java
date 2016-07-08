package com.example.test.baseforaproject;

import android.app.Application;
import android.content.Context;
import com.example.test.baseforaproject.preference.AppPreference;
import com.example.test.baseforaproject.webservice.HttpClient;

public class BaseApp extends Application{

    private static Context mContext;
    private AppPreference mPreference ;
    private HttpClient httpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mPreference = new AppPreference(mContext);
        httpClient = new HttpClient(mContext);
    }

    public AppPreference getAppPrefrence(){
        return mPreference;
    }

    public HttpClient getHttpClient(){
        return httpClient;
    }

}
