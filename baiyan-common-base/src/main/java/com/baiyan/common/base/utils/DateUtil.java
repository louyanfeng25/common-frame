package com.baiyan.common.base.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 时间工具类
 *
 * @author baiyan
 * @date 2020/11/13
 */
public class DateUtil extends cn.hutool.core.date.DateUtil {

    private static final String HOUR = "hour";

    private static final String DAY = "day";

    private static final String MONTH = "month";

    private static final String YEAR = "year";

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final LocalTime MAX_LOCAL_TIME = LocalTime.of(23, 59, 59);

    private static final ThreadLocal<DateFormat> DATE_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS));

    private static ZoneId zoneId = ZoneId.systemDefault();

    public static String getType(LocalDateTime start, LocalDateTime end) {
        long diff = DateUtil.getTimeDifference(start, end);
        if (diff < 24 * 60 * 60) {
            return HOUR;
        } else if (diff < 3L * 30 * 24 * 60 * 60) {
            return DAY;
        } else if (diff < 2L * 12 * 30 * 24 * 60 * 60) {
            return MONTH;
        } else {
            return YEAR;
        }
    }

    public static List<LocalDateTime> getXAxis(LocalDateTime startTime, LocalDateTime endTime, String type) {
        if (StringUtil.isEmpty(type)) {
            type = getType(startTime, endTime);
        }
        List<LocalDateTime> xAxis = new ArrayList<>();
        while (startTime.isBefore(endTime) || startTime.isEqual(endTime)) {
            xAxis.add(startTime);
            startTime = getTimeByType(startTime, type);
        }
        return xAxis;
    }

    private static LocalDateTime getTimeByType(LocalDateTime time, String type) {
        if (HOUR.equals(type)) {
            time = time.plusHours(1);
        } else if (MONTH.equals(type)) {
            time = time.plusMonths(1);
        } else if (YEAR.equals(type)) {
            time = time.plusYears(1);
        } else {
            time = time.plusDays(1);
        }
        return time;
    }

    public static String getDateFormat(String type) {
        if (HOUR.equals(type)) {
            return "HH";
        } else if (MONTH.equals(type)) {
            return "yyyy/MM";
        } else if (YEAR.equals(type)) {
            return "yyyy";
        } else {
            return "yyyy/MM/dd";
        }
    }

    public static long getTimeDifference(LocalDateTime startTime, LocalDateTime endTime) {
        return startTime.until(endTime, ChronoUnit.SECONDS);
    }

    public static long getMinuteDifference(LocalDateTime startTime, LocalDateTime endTime) {
        return startTime.until(endTime, ChronoUnit.MINUTES);
    }

    public static long getDayDifference(LocalDateTime startTime, LocalDateTime endTime) {
        return startTime.until(endTime, ChronoUnit.DAYS);
    }

    public static long getDayDifference(LocalDate start, LocalDate end) {
        return start.until(end, ChronoUnit.DAYS);
    }

    /**
     * LocalDateTime转string
     */
    public static String formatTimeString(LocalDateTime time, String pattern) {
        if (org.springframework.util.StringUtils.isEmpty(pattern)) {
            pattern = YYYY_MM_DD_HH_MM_SS;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        if (time == null) {
            return null;
        }

        return time.format(formatter);
    }

    /**
     * LocalDateTime转string
     */
    public static String formatTimeString(LocalDateTime time) {
        return formatTimeString(time, YYYY_MM_DD_HH_MM_SS);
    }

    public static String formatTimeStringForEs(Long ms) {
        if (null == ms) {
            ms = System.currentTimeMillis();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String dateStr = sdf.format(new Date(ms));
        return dateStr;
    }

    public static String formatTimeNow(String format) {
        return formatTimeNow(null, format);
    }

    public static String formatTimeNow(Long ms, String format) {
        if (null == ms) {
            ms = System.currentTimeMillis();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String dateStr = sdf.format(new Date(ms));
        return dateStr;
    }

    /**
     * LocalDate转string
     */
    public static String formatDateString(LocalDate time, String pattern) {
        if (time == null) {
            return null;
        }
        if (org.springframework.util.StringUtils.isEmpty(pattern)) {
            pattern = YYYY_MM_DD;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return time.format(formatter);
    }

    /**
     * LocalDate转string
     */
    public static String formatDateString(LocalDate time) {
        return formatDateString(time, YYYY_MM_DD);
    }

    public static int getHours(LocalDateTime startTime, LocalDateTime endTime) {
        long hours = startTime.until(endTime, ChronoUnit.HOURS);
        return (int) hours;
    }

    public static LocalDateTime dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    public static char formatDigit(char sign) {
        switch (sign) {
            case '0':
                sign = '〇';
                break;
            case '1':
                sign = '一';
                break;
            case '2':
                sign = '二';
                break;
            case '3':
                sign = '三';
                break;
            case '4':
                sign = '四';
                break;
            case '5':
                sign = '五';
                break;
            case '6':
                sign = '六';
                break;
            case '7':
                sign = '七';
                break;
            case '8':
                sign = '八';
                break;
            case '9':
                sign = '九';
                break;
        }
        return sign;
    }

    public static String formatStr(String str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            sb.append(formatDigit(str.charAt(i)));
        }
        sb.append("年");
        if (str.charAt(5) == '0') {
            sb.append(formatDigit(str.charAt(6)));
        } else {
            sb.append(formatDigit(str.charAt(5)));
            sb.append("十");
            sb.append(formatDigit(str.charAt(6)));
        }
        sb.append("月");
        return sb.toString();
    }

    /**
     * 收集起始时间到结束时间之间所有的时间并以字符串集合方式返回
     *
     * @param startTime
     * @param endTime
     * @param pattern
     * @return
     */
    public static List<String> collectLocalDates(LocalDateTime startTime, LocalDateTime endTime, String pattern) {
        if (Objects.isNull(startTime)) {
            return new ArrayList<>();
        }
        if (Objects.isNull(endTime)) {
            endTime = LocalDateTime.now();
        }
        // 用起始时间作为流的源头，按照每次加一天的方式创建一个无限流
        return Stream.iterate(startTime, localDateTime -> localDateTime.plusDays(1))
                // 截断无限流，长度为起始时间和结束时间的差+1个
                .limit(ChronoUnit.DAYS.between(startTime, endTime) + 1)
                // 由于最后要的是字符串，所以map转换一下
                .map(x -> formatTimeString(x, pattern))
                // 把流收集为List
                .collect(Collectors.toList());
    }

    /**
     * string 转 LocalDateTime
     *
     * @param time
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String time) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
        return LocalDateTime.parse(time, df);
    }

    public static LocalDateTime stringToLocalDateTime(String time, String pattern) {
        if (org.springframework.util.StringUtils.isEmpty(pattern)) {
            pattern = YYYY_MM_DD_HH_MM_SS;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(time, df);
    }

    public static LocalDate stringToLocalDate(String time) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(YYYY_MM_DD);
        return LocalDate.parse(time, df);
    }

    public static LocalDate stringToLocalDate(String time, String pattern) {
        if (org.springframework.util.StringUtils.isEmpty(pattern)) {
            pattern = YYYY_MM_DD;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(time, df);
    }

    public static Date parseString(String date) {
        try {
            return DATE_FORMAT.get().parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * yyyy-MM-dd'T'HH:mm:ss:SSSZ转换成gmt+8的LocalDateTime
     */
    public static LocalDateTime getISODateTime(String dateString) {
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_INSTANT;
        Instant dateInstant = Instant.from(isoFormatter.parse(dateString));
        return LocalDateTime.ofInstant(dateInstant, ZoneId.of("Asia/Shanghai"));
    }

    /**
     * localdatetime 转date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        String time = formatTimeString(localDateTime, YYYY_MM_DD_HH_MM_SS);
        return parseString(time);
    }

    /**
     * 当天的最大时间
     */
    public static LocalDateTime getMaxTime(LocalDate date) {
        return LocalDateTime.of(date, MAX_LOCAL_TIME);
    }

    /**
     * 将秒转化成X天X小时X分X秒形式
     */
    public static String minuteToTime(Long seconds){
        if(Objects.isNull(seconds)){
            return null;
        }
        long day = seconds/(60*60*24);
        long hour = (seconds-day*24*60*60)/(60*60);
        long min = (seconds-hour*60*60-day*24*60*60)/60;
        long second = seconds-min*60-hour*60*60-day*24*60*60;
        StringBuilder sb = new StringBuilder();
        JavaUtil.getJavaUtil()
                .acceptIfCondition(day!=0,day,e->sb.append(e).append("天"))
                .acceptIfCondition(hour!=0,hour,e->sb.append(e).append("小时"))
                .acceptIfCondition(min!=0,min,e->sb.append(e).append("分"))
                .acceptIfCondition(second!=0,second,e->sb.append(e).append("秒"));
        return sb.toString();
    }

}
