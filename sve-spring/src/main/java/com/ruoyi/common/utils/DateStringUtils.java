package com.ruoyi.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStringUtils {

    public static Date stringToDate(String format, String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String formatDateStr(String str) {
        if (!StringUtils.isEmpty(str))
            return str.split(" ")[0];
        else
            return str;
    }

    public static String formatTimeStr(String str) {
        if (!StringUtils.isEmpty(str))
            return str.substring(0,19);
        else
            return str;
    }

    public static String dateToString(String format, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String LongToDate(String format, String millSec) {
        Long l = Long.parseLong(millSec);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date(l);
        return sdf.format(date);
    }
}
