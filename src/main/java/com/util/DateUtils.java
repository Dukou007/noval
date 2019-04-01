package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sunxusen on 2018/7/22.
 */
public class DateUtils {

    public final static String PATTERN_S = "yyyy-MM-dd HH:mm:ss";
    public final static String PATTERN_MS = "yyyy-MM-dd HH:mm:ss.SSS";

    public static long now() {
        return System.currentTimeMillis();
    }

    public static Date nowDate() {
        return new Date(now());
    }

    public static String format(Date date) {
        return format(date, PATTERN_S);
    }

    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

}
