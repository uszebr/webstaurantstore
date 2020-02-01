package com.webstaurantstore.basetest.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

abstract public class TimeStamp {
    public static String  getTimeStamp(){
        String timeStamp = new SimpleDateFormat("MM_dd-HH_mm_ss-SSS").format(new Date());//time stamp
        //Month_day-Hours_minute_seconds-mSeconds

        return timeStamp;
    }
}
