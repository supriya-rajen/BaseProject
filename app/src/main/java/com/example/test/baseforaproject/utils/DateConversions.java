package com.example.test.baseforaproject.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by test on 05-07-2016.
 */
public class DateConversions {
    public static String getCurrentTime(String format) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(c.getTime());
    }
}
