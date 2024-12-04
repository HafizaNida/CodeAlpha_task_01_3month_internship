package com.example.collegealertapplication.utils;

import android.content.Context;
import android.text.format.DateFormat;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

/*A class that will contain static functions, constants, variables that we will be used in whole application*/
public class Utils {

    private static final String TAG = "UTILS_TAG";




    //Conditions array of the Ads
    public static final String[] conditions = {"New", "Used", "Refurbished"};


    /** A Function to show Toast
     @param context the context of activity/fragment from where this function will be called
     @param message the message to be shown in the Toast */
    public static void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /** A Function to get current timestamp
     @return Returns the current timestamp as long datatype
     */
    public static long getTimestamp(){
        return System.currentTimeMillis();
    }

    /**
     @param timestamp the timestamp of type Long that we need to format to dd/MM/yyyy
     @return timestamp formatted to date dd/MM/yyyy*/
    public static String formatTimestampDate(Long timestamp){
        if (timestamp == null) {
            return "00/00/00"; // or some default value
        }
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(timestamp);
        String date = DateFormat.format("dd/MM/yyyy", calendar).toString();
        return date;
    }

}
