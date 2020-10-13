package TimeUtil;

import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @Author: yichuan
 * @Date: 2020/10/13 8:37 下午
 * @Description:
 */
public class TimeUtil {
    /**
     * 时间戳转日期
     *
     * @param timestamp 时间戳 毫秒
     */
    public static String getDateStrByTimeStamp(Long timestamp) {
        if (timestamp == null) {
            return "";
        }
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String getDateStr(long daysToSubtract) {
        return LocalDate.now().minusDays(daysToSubtract).format(DateTimeFormatter.BASIC_ISO_DATE);
    }


    /**
     * 获取秒数
     */
    public static int getSeconds(LocalDateTime localDateTime) {
        return (int) localDateTime.toEpochSecond(ZoneOffset.of("+8"));
    }

    /**
     * 时间戳转日期
     *
     * @param time 时间戳 1545635941000
     */
    public static LocalDateTime getLocalDateTime(long time) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneOffset.of("+8"));
    }


    /**
     * 字符串传日期
     *
     * @param date 20181212
     */
    public static LocalDate getLocalDate(Integer date) {
        return LocalDate.parse(String.valueOf(date), DateTimeFormatter.BASIC_ISO_DATE);
    }


    public static String getLocalDateStr(Integer date, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            pattern = "yyyy-MM-dd";
        }
        return getLocalDate(date).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getLocalDateStr(LocalDate localDate, String pattern) {
        if (StringUtils.isBlank(pattern)) {
            pattern = "yyyy-MM-dd";
        }
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getLocalDateStr(LocalDate localDate) {
        return getLocalDateStr(localDate, null);
    }

    public static String getLocalDateStr(Integer date) {
        return getLocalDateStr(date, "yyyy-MM-dd");
    }

    /**
     * 时间戳转日期
     *
     * @param millisecond 毫秒
     */
    public static String getDateStrBySeconds(Long millisecond, String pattern) {
        if (null == millisecond || millisecond <= 0) {
            return "-";
        }
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millisecond), ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * @return
     */
    public static String getYesterday() {
        return getDeltaFormatToday(1, "yyyy-MM-dd");
    }

    /**
     * 获取日期 可以指定格式
     * currentTime 精确到秒
     */
    public static String getDeltaFormatToday(int nDays, String formatter) {
        LocalDate date = LocalDate.now().minus(nDays, ChronoUnit.DAYS);
        DateTimeFormatter formate = DateTimeFormatter.ofPattern(formatter);
        return date.format(formate);
    }
}
