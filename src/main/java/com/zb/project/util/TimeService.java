package com.zb.project.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Reduce the calculation of Date:
 * <p>
 * 1. Reduce the calls of System.currentTimeMillis()
 * 2. Reduce the calculation of the beginning of a day
 *
 * @author Hover Ruan
 */
@Service
public class TimeService {
    private static Logger logger = LoggerFactory.getLogger(TimeService.class);
    public static final long SECOND = 1000;
    public static final long MINUTE = 60 * SECOND;
    public static final long HOUR = 60 * MINUTE;
    public static final int HOUR_IN_SECOND = (int) (HOUR / SECOND);
    public static final int MINUTE_IN_SECOND = (int) (MINUTE / SECOND);
    public static final long DAY = 24 * HOUR;
    public static final int DAY_IN_SECOND = (int) (DAY / SECOND);
    public static final long WEEK = 7 * DAY;
    public static final long MONTH = 24 * DAY;
    private static DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static DateFormat ddf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static DateFormat ymd = new SimpleDateFormat("yyyyMMdd");

    public static Date getNow() {
        return new Date();
    }

    public static Date getNow(boolean withoutMilliSecond) {
        Calendar c = Calendar.getInstance();
        if (withoutMilliSecond) {
            c.set(Calendar.MILLISECOND, 0);
        }
        return c.getTime();
    }

    public static Date getYesterday() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        return c.getTime();
    }

    public static int getDayBetween(Date start, Date end) {
        long d = Math.abs(end.getTime() - start.getTime());
        return (int) (d / (1000 * 60 * 60 * 24) + 0.5);
    }

    public static int getMinuteBetween(Date start, Date end) {
        long d = Math.abs(end.getTime() - start.getTime());
        return (int) (d / (1000 * 60) + 0.5);
    }

    public static Date getDayBeginning() {
        return getTodayStart();
    }


    public static Date getFirstDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }


    public static Date getDateStart(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date getDateEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }


    public static Date[] getEveryDay(Date start, Date end) {
        List<Date> dayList = new ArrayList<Date>();
        Calendar c = Calendar.getInstance();

        int i = 0;
        Date step = start;
        while (step.before(end)) {
            c.setTime(start);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            c.add(Calendar.DATE, i++);
            step = c.getTime();
            dayList.add(step);
        }
        return dayList.toArray(new Date[0]);
    }

    public static Date[] getEveryDay(Date start, int days) {
        int size = Math.abs(days);
        Date[] dateList = new Date[size];
        Calendar c = Calendar.getInstance();

        if (days == 0) {
            return dateList;
        } else if (days > 0) {
            for (int i = 0; i < size; i++) {
                c.setTime(start);
                c.set(Calendar.HOUR_OF_DAY, 0);
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND, 0);
                c.set(Calendar.MILLISECOND, 0);
                c.add(Calendar.DATE, i);
                dateList[i] = c.getTime();
            }
        } else {
            for (int i = 0; i < size; i++) {
                c.setTime(start);
                c.set(Calendar.HOUR_OF_DAY, 0);
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND, 0);
                c.set(Calendar.MILLISECOND, 0);
                c.add(Calendar.DATE, days + i + 1);
                dateList[i] = c.getTime();
            }
        }
        return dateList;
    }

    public static Date getDayAfter(Date date, int i) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, i);
        return c.getTime();
    }

    public static Date getDayAt(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static String formatDate(Date date, String parse) {
        DateFormat df = new SimpleDateFormat(parse);
        try {
            return df.format(date);
        } catch (Exception e) {

        }
        return null;
    }

    public static Date parseDate(String date, String partner) {
        DateFormat df = new SimpleDateFormat(partner);
        try {
            return df.parse(date);
        } catch (ParseException e) {

        }
        return null;
    }

    /**
     * yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        try {
            return sdf.format(date);
        } catch (Exception e) {

        }
        return null;
    }

    public static String formatDDate(Date date) {
        try {
            return ddf.format(date);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * yyyyMMdd
     *
     * @param
     * @return
     */
    public static String formatNow() {
        try {
            return ymd.format(new Date());
        } catch (Exception e) {

        }
        return null;
    }

    public static Date parseDDate(String date) {
        try {
            return ddf.parse(date);
        } catch (ParseException e) {

        } catch (Exception e) {

        }
        return null;
    }

    /**
     * yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static Date parseDate(String date) {
        try {
            return sdf.parse(date);
        } catch (ParseException e) {

        } catch (Exception e) {

        }
        return null;
    }

    public static Date parseDate(Object dateObj) {
        try {
            String date = String.valueOf(dateObj);
            String partner = null;
            if (StringUtils.contains(date, ":")) {
                int c1 = date.length() - StringUtils.remove(date, ":").length();
                switch (c1) {
                    case 0:
                        partner = "yyyy-MM-dd";
                        break;
                    case 1:
                        partner = "yyyy-MM-dd HH:mm";
                        break;
                    case 2:
                        partner = "yyyy-MM-dd HH:mm:ss";
                        break;
                }
                return parseDate(date, partner);
            }
            return sdf.parse(date);
        } catch (ParseException e) {

        } catch (Exception e) {

        }
        return null;
    }

    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime();
    }

    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return c.getTime();
    }


    public static Date getTodayStart() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date getTodayEnd() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    public static Date getWeekStart() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.DAY_OF_WEEK, (-1) * c.get(Calendar.DAY_OF_WEEK) + 1);
        return c.getTime();
    }

    public static Date getWeekStart(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.DAY_OF_WEEK, (-1) * c.get(Calendar.DAY_OF_WEEK) + 1);
        return c.getTime();
    }

    public static Date getWeekStart(Integer week) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.WEEK_OF_YEAR, week);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return c.getTime();
    }

    public static Date getWeekEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        return c.getTime();
    }

    public static Date getWeekEnd() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        return c.getTime();
    }

    public static Date getWeekEnd(int week) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.WEEK_OF_YEAR, week);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        return c.getTime();
    }

    /**
     * 获取当前日期是星期几
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    /**
     * 获取当前年份
     */
    public static Long getSysYear() {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        return Long.parseLong(year);
    }

    /**
     * 获取上一年
     */
    public static Long getSysLastYear() {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        Integer time = Integer.parseInt(year) - 1;
        return Long.parseLong(time.toString());
    }

    /**
     * 获取下一年第一天
     */
    public static Date getNextYearFirstDay(Long year) {
        return getDayAt(Integer.parseInt(year.toString()) + 1, 0, 1);
    }

    /**
     * 获取当前年第一天
     */
    public static Date getYearFirstDay(Long year) {
        return getDayAt(Integer.parseInt(year.toString()), 0, 1);
    }


    /**
     * 获取当前年份第一天
     */
    public static Date getSysYearFirstDay() {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        return getDayAt(Integer.parseInt(year), 0, 1);
    }


    /**
     * 获取当前月份
     */
    public static Long getSysMonth() {
        Calendar date = Calendar.getInstance();
        String month = String.valueOf(date.get(Calendar.MONTH) + 1);
        return Long.getLong(month);
    }


    public static Date getDate(String date, int hour, int min) {
        Calendar c = Calendar.getInstance();
        c.setTime(parseDate(date));
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, min);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMin(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

}
