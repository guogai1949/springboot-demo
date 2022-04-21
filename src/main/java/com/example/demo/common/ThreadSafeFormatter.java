package com.example.demo.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadSafeFormatter {

    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {

        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

    };

    public static String format(Date date) {
        return dateFormatThreadLocal.get().format(date);
    }

    public static Date parse(String date) throws ParseException {
        return  dateFormatThreadLocal.get().parse(date);
    }
}
