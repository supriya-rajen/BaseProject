package com.example.test.baseforaproject;


import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.baseforaproject.base.BaseActivity;
import com.example.test.baseforaproject.leftmenu.HomeActivity;
import com.example.test.baseforaproject.utils.DateConversions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends BaseActivity {
    Button btnLogin;
    TextView txtLoggedInTime,txtloggedoutTime;
    public static String loggedintime;
    private RadioButton radio24Hours, radio12Hours;
    public static Boolean is24HourFormat = false;
    TextClock textClock,textDate;
    private Calendar calendar,cal;
    long loggedInTimeinMilliSec;

    @Override
    public void onRightIconClick() {
        super.onRightIconClick();
        finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setupEvent();
        setupDefault();
    }

    private void init() {
        setPageTitle("Log in");
        setRightImage(R.drawable.ic_close_white);
        btnLogin = (Button) findViewById(R.id.login);
        textClock = (TextClock) findViewById(R.id.timerr);

        txtLoggedInTime = (TextView) findViewById(R.id.logged_in_time);
        textDate = (TextClock) findViewById(R.id.date);
        radio12Hours = (RadioButton) findViewById(R.id.hour_12);
        radio24Hours = (RadioButton) findViewById(R.id.hour_24);
        txtloggedoutTime = (TextView) findViewById(R.id.logged_out_time);

        cal = Calendar.getInstance();
    }

    private void setupDefault() {
        textDate.setFormat12Hour("dd-MM-yyyy");
        textClock.setFormat12Hour("hh:mm:ss a");

        if((getApp().getAppPrefrence().getLoggedInTime() != 0L) && (getApp().getAppPrefrence().getLoggedOutTime() != 0L)){
            validateDay();
        }
    }

    private void setupEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstTimeLogged = textClock.getText()+" "+textDate.getText();
                Date date = null;
                SimpleDateFormat format;

              if(is24HourFormat){
                  format = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
              }else{
                  format = new SimpleDateFormat("hh:mm:ss a dd-MM-yyyy");
              }

                try {
                    date = format.parse(firstTimeLogged);
                    loggedInTimeinMilliSec = date.getTime();
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                getApp().getAppPrefrence().setLoggedInTime(loggedInTimeinMilliSec);

                cal.add(Calendar.DAY_OF_YEAR, 1);
                cal.set(Calendar.HOUR_OF_DAY,0);
                cal.set(Calendar.MINUTE,0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                long tomorrowTimeInMilliseconds = cal.getTimeInMillis();
                getApp().getAppPrefrence().setLoggedOutTime(tomorrowTimeInMilliseconds);

                loggedintime = (String)textClock.getText();
                Toast.makeText(MainActivity.this, "Logged Time :" + textClock.getText(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                finish();
            }
        });

        radio24Hours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is24HourFormat = true;
                textClock.setFormat12Hour(null);
                textClock.setFormat24Hour("HH:mm:ss");
            }
        });

        radio12Hours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is24HourFormat = false;
                textClock.setFormat12Hour("hh:mm:ss a");
                textClock.setFormat24Hour(null);
            }
        });
    }

    private void validateDay() {
        String dtStart = textClock.getText()+" "+textDate.getText();
        Date date = null;
        SimpleDateFormat format;

        if(is24HourFormat){
            format = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        }else{
            format = new SimpleDateFormat("hh:mm:ss a dd-MM-yyyy");
        }

        try {
            date = format.parse(dtStart);
            loggedInTimeinMilliSec = date.getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

      if (((getApp().getAppPrefrence().getLoggedInTime()) < loggedInTimeinMilliSec)&&(loggedInTimeinMilliSec < getApp().getAppPrefrence().getLoggedOutTime()) ) {

            Toast.makeText(MainActivity.this,"second time",Toast.LENGTH_LONG).show();
            Intent mainIntent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(mainIntent);
            finish();
      } else {
            getApp().getAppPrefrence().logout();
        }

    }


}
