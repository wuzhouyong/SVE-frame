package com.ruoyi.common.utils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 时间工具类
 *
 * @author ruoyi
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format) {
        return parseDate(new Date(), format);
    }

    /**
     * 把Date转换成yyyy-MM-dd 时间格式
     */
    public static final String dateTime(final Date date) {
        return parseDate(date, YYYY_MM_DD);
    }

    /**
     * 把Date转换成yyyy-MM-dd HH:mm:ss 时间格式
     */
    public static final String dateTimeS(final Date date) {
        return parseDate(date, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 把String转换成yyyy-MM-dd Date格式
     */
    public static final Date getTime(final String str) {
        return dateTime(str, YYYY_MM_DD);
    }

    /**
     * 把String转换成yyyy-MM-dd HH:mm:ss Date格式
     */
    public static final Date getTimeS(final String str) {
        return dateTime(str, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 把Date转换成指定的格式 如:yyyy-MM-dd HH:mm:ss 转换成时间格式
     */
    public static final String parseDate(final Date date, final String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 把String按照指定的格式转成Date格式 如:yyyy-MM-dd HH:mm:ss
     */
    public static final Date dateTime(final String ts, final String format) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date begineDate, Date endDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - begineDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 计算两个时间差 得多少天
     */
    public static int getDayPoor(Date begineDate, Date endDate) {
        long diff = endDate.getTime() - begineDate.getTime();
        return Math.toIntExact(diff / (1000 * 60 * 60 * 24));
    }

    /**
     * 计算两个时间差 得多少天
     */
    public static int getDayPoor(String begineDate, String endDate) {
        Date begineTime = getTimeS(begineDate);
        Date endTime = getTimeS(endDate);
        long diff = endTime.getTime() - begineTime.getTime();
        return Math.toIntExact(diff / (1000 * 60 * 60 * 24));
    }

    /**
     * 计算两个时间差 得小时
     */
    public static int getHourPoor(Date begineDate, Date endDate) {
        long diff = endDate.getTime() - begineDate.getTime();
        return Math.toIntExact(diff / (1000 * 60 * 60));
    }

    /**
     * 计算两个时间差 得小时
     */
    public static int getHourPoor(String begineDate, String endDate) {
        Date begineTime = getTimeS(begineDate);
        Date endTime = getTimeS(endDate);
        long diff = endTime.getTime() - begineTime.getTime();
        return Math.toIntExact(diff / (1000 * 60 * 60));
    }

    /**
     * 计算两个时间差 得分钟
     */
    public static int getMinutesPoor(Date begineDate, Date endDate) {
        long diff = endDate.getTime() - begineDate.getTime();
        return Math.toIntExact(diff / (1000 * 60));
    }

    /**
     * 计算两个时间差 得分钟
     */
    public static int getMinutesPoor(String begineDate, String endDate) {
        Date begineTime = getTimeS(begineDate);
        Date endTime = getTimeS(endDate);
        long diff = endTime.getTime() - begineTime.getTime();
        return Math.toIntExact(diff / (1000 * 60));
    }

    /**
     * 把时间加count小时
     */
    public static String getAddHour(Date date, int count) {
        long nd = count * 60 * 60 * 1000;
        //  long nd = count * 24 * 60 * 60 * 1000;//增加count天
        return dateTimeS(new Date(date.getTime() + nd));
    }

    /**
     * 把时间加count小时
     */
    public static Date getAddHour(String date, int count) {
        Date tempTime = getTimeS(date);
        long nd = count * 60 * 60 * 1000;
        //  long nd = count * 24 * 60 * 60 * 1000;//增加count天
        return new Date(tempTime.getTime() + nd);
    }

    /**
     * 把时间加count天
     */
    public static Date getAddDay(String date, int count) {
        Date tempTime = getTimeS(date);
        long nd = count * 24 * 60 * 60;//增加count天
        return Date.from(tempTime.toInstant().plusSeconds(nd));
    }

    /**
     * 把时间加count天
     */
    public static String getAddDay(Date date, int count) {
        long nd = count * 24 * 60 * 60;//增加count天
        Date from = Date.from(date.toInstant().plusSeconds(nd));
        return dateTimeS(from);
    }

    //java日期增加或减少天，当days为负数时为减少的天数，当days为正整数时为增加的天数
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        Date newDay = cal.getTime();
        return newDay;
    }

    //java日期增加或减少月，当months为负数时为减少的月数，当days为正整数时为增加的月数
    public static Date addMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        Date newDay = cal.getTime();
        return newDay;
    }

    //java日期增加或减少小时，当months为负数时为减少的小时数，当days为正整数时为增加的小时数
    public static Date addHours(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        Date newDay = cal.getTime();
        return newDay;
    }

    //java日期增加或减少分钟，当months为负数时为减少的分钟数，当days为正整数时为增加的分钟数
    public static Date addMinutes(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        Date newDay = cal.getTime();
        return newDay;
    }

    /**
     * 把格林威治的时间改为北京时间,加8个小时 变为字符串
     */
    public String UTCToLocalDate(Date utcDate) {
        //把UTC时间格式转变为date类型
        String str = utcDate.toString();
        str = str.substring(0, str.lastIndexOf("."));
        str = str.replace("T", " ");
        Date tempTime = getTimeS(str);
        return dateTimeS(new Date(tempTime.getTime() + 60 * 60 * 8 * 1000));
    }

    /**
     * 把格林威治的时间改为北京时间,加8个小时 变为date
     */
    public Date UTCToLocalDate(String str) {
        // String str = "2018-02-12T14:41:37.000+0000";
        str = str.substring(0, str.lastIndexOf("."));
        str = str.replace("T", " ");
        Date utcDate = getTimeS(str);
        return new Date(utcDate.getTime() + 60 * 60 * 8 * 1000);
    }

    /**
     * 把入参时间 2020-08-09 00:00:00.0 改为正常的时间
     * return  2020-08-09 00:00:00
     */

    public static String formatTimeStrS(String str) {
        if (!StringUtils.isEmpty(str))
            return str.substring(0, 19);
        else
            return str;
    }

    /**
     * 把入参时间 2020-08-09 00:00:00.0 改为正常的时间
     * return  2020-08-09
     */

    public static String formatTimeStr(String str) {
        if (!StringUtils.isEmpty(str))
            return str.substring(0, 10);
        else
            return str;
    }

    /**
     * 把入参时间 2020-08-09 00:00:00.0 改为正常的时间
     * return  2020-08-09
     */
    public static String formatDateStr(String str) {
        if (!StringUtils.isEmpty(str))
            return str.split(" ")[0];
        else
            return str;
    }

}
