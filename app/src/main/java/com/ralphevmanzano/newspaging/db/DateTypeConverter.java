package com.ralphevmanzano.newspaging.db;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.room.TypeConverter;

public class DateTypeConverter {
    @TypeConverter
    public static String fromTimestamp(Long longDate) {
        @SuppressLint("SimpleDateFormat")
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        return longDate == null ? "" : dateFormat.format(new Date(longDate));
    }

    @SuppressLint("SimpleDateFormat")
    @TypeConverter
    public static Long dateToTimestamp(String strDate) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date == null ? null : date.getTime();
    }
}
