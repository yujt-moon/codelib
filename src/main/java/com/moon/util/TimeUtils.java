package com.moon.util;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间工具类
 * @author yujiangtao
 * @since 1.0
 */
public class TimeUtils {

    /**
     * 根据格式化字符串，格式化日期
     * @param date 日期
     * @param format 格式化字符串
     * @return 格式化后的日期字符串
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 格式化当前日期
     * @param format 格式化字符串
     * @return
     */
    public static String formatCurrentDate(String format) {
        return formatDate(new Date(), format);
    }

    /**
     * 查询开始日期和结束日期之间的日期
     * @param start 开始日期
     * @param end 结束日期
     * @return 日期列表
     */
    public static List<Date> getBetweenDates(Date start, Date end) {
        if (start.compareTo(end) >= 0) {
            throw new IllegalArgumentException("结束日期必须大于开始日期");
        }
        List<Date> result = new ArrayList<Date>();
        result.add(start);
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
        tempStart.add(Calendar.DAY_OF_YEAR, 1);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        while (tempStart.before(tempEnd)) {
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        result.add(end);
        return result;
    }

    public static void getMonthRange(String yearMonth) {
        String beginDateStr = yearMonth + "-01";
        Date beginDate = null;
        try {
            beginDate = DateUtils.parseDate(beginDateStr, "yyyy-MM-dd");
        } catch (ParseException e) {
            throw new IllegalArgumentException("日期格式错误");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDate);
        calendar.add(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.add(Calendar.MILLISECOND, -1);
        Date endDate = calendar.getTime();

        System.out.println(TimeUtils.formatDate(beginDate, "yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(TimeUtils.formatDate(endDate, "yyyy-MM-dd HH:mm:ss.SSS"));
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse("2021-10-1");
        Date end = sdf.parse("2021-12-3");
        List<Date> betweenDates = getBetweenDates(start, end);
        betweenDates.forEach(x -> System.out.println(sdf.format(x)));

        getMonthRange("2022-01");
    }
}
