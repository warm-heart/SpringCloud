package com.micro.commons.util;



import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * 时间工具类
 *
 * @author wangqianlong
 */

public class DateUtils {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
    private static final DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter dateTimeFormatter3 = DateTimeFormatter.ofPattern("yyyy年MM月dd日");


    /**
     * 日期转字符串 yyyy-MM-dd HH:mm:ss格式
     *
     * @param localDateTime
     * @return
     */
    public final static String dateTimeConvertToString(LocalDateTime localDateTime) {
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 日期转字符串 yyyy年MM月dd日 HH:mm:ss格式
     *
     * @param localDateTime
     * @return
     */
    public final static String dateTimeConvertToString1(LocalDateTime localDateTime) {
        return localDateTime.format(dateTimeFormatter1);
    }

    /**
     * 日期转字符串 yyyy-MM-dd 格式
     *
     * @param localDateTime
     * @return
     */
    public final static String dateTimeConvertToString2(LocalDateTime localDateTime) {
        return localDateTime.format(dateTimeFormatter2);
    }

    /**
     * 日期转字符串 yyyy年MM月dd日 格式
     *
     * @param localDateTime
     * @return
     */
    public final static String dateTimeConvertToString3(LocalDateTime localDateTime) {
        return localDateTime.format(dateTimeFormatter3);
    }


    /**
     * 字符串转日期 yyyy-MM-dd HH:mm:ss格式
     *
     * @param localDateTime
     * @return
     */
    public final static LocalDateTime stringConvertToDateTime(String localDateTime) {
        return LocalDateTime.parse(localDateTime, dateTimeFormatter);
    }

    /**
     * 字符串转日期 yyyy年MM月dd日 HH:mm:ss格式
     *
     * @param localDateTime
     * @return
     */
    public final static LocalDateTime stringConvertToDateTime1(String localDateTime) {
        return LocalDateTime.parse(localDateTime, dateTimeFormatter1);
    }

    /**
     * 字符串转日期 yyyy-MM-dd 格式
     *
     * @param localDateTime
     * @return
     */
    public final static LocalDate stringConvertToDateTime2(String localDateTime) {
        LocalDate localDate = LocalDate.parse(localDateTime, dateTimeFormatter2);
        return localDate;
    }

    /**
     * 字符串转日期 yyyy年MM月dd日 格式
     *
     * @param localDateTime
     * @return
     */
    public final static LocalDate stringConvertToDateTime3(String localDateTime) {
        return LocalDate.parse(localDateTime, dateTimeFormatter3);
    }


    /**
     * localDate转LocalDateTime Time为当前时间
     *
     * @param localDate
     * @return
     */
    public final static LocalDateTime localDateConvertToLocalDateTime(LocalDate localDate) {

        //return localDate.atStartOfDay();    //Time为00：00：00
        return localDate.atTime(LocalTime.now());
    }

    /**
     * localDateTime转LocalDate
     *
     * @param localDateTime
     * @return
     */
    public final static LocalDate localDateTimeConvertToLocalDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }


    /**
     * 获取一个月的第一天
     *
     * @param localDateTime
     * @return
     */
    public final static LocalDateTime getFirstDayOfMonth(LocalDateTime localDateTime) {
        return localDateTime.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取一个月的最后一天
     *
     * @param localDateTime
     * @return
     */
    public final static LocalDateTime getLastDayOfMonth(LocalDateTime localDateTime) {
        return localDateTime.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 加小时数
     *
     * @param localDateTime
     * @param hours
     * @return
     */
    public final static LocalDateTime plusHours(LocalDateTime localDateTime, long hours) {
        return localDateTime.plusHours(hours);
    }

    /**
     * 加天数
     *
     * @param localDateTime
     * @param days
     * @return
     */
    public final static LocalDateTime plusDays(LocalDateTime localDateTime, long days) {
        return localDateTime.plusDays(days);
    }


    /**
     * 加周数
     *
     * @param localDateTime
     * @param weeks
     * @return
     */
    public final static LocalDateTime plusWeeks(LocalDateTime localDateTime, long weeks) {
        return localDateTime.plusWeeks(weeks);
    }

    /**
     * 加月数
     *
     * @param localDateTime
     * @param months
     * @return
     */
    public final static LocalDateTime plusMonths(LocalDateTime localDateTime, long months) {
        return localDateTime.plusMonths(months);
    }

    /**
     * 加年数
     *
     * @param localDateTime
     * @param years
     * @return
     */
    public final static LocalDateTime plusYears(LocalDateTime localDateTime, long years) {
        return localDateTime.plusYears(years);
    }

    /**
     * 判断今天是否是生日
     *
     * @param localDateTime 生日日期
     * @return
     */
    public final static boolean isBirthDay(LocalDateTime localDateTime) {
        MonthDay monthDay = MonthDay.of(localDateTime.getMonth(), localDateTime.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(LocalDateTime.now());
        if (currentMonthDay.equals(monthDay)) {
            return true;
        }
        return false;
    }

    /**
     * 计算时间差
     *
     * @param startDate 开始日期（距离现在最远的时间）
     * @param endDate   结束日期（距离现在最近的时间）
     * @return
     */
    public final static Period calculationTime(LocalDateTime startDate, LocalDateTime endDate) {
        LocalDate startDateInclusive = startDate.toLocalDate();
        LocalDate endDateExclusive = endDate.toLocalDate();
        Period period = Period.between(startDateInclusive, endDateExclusive);
        System.err.println("相差" + period.getYears() + "年");
        System.err.println("相差" + period.getMonths() + "月");
        System.err.println("相差" + period.getDays() + "日");
        return period;
    }


    public static void main(String[] args) {
//        System.err.println(dateTimeConvertToString(LocalDateTime.now()));
//        System.err.println(dateTimeConvertToString1(LocalDateTime.now()));
//        System.err.println(dateTimeConvertToString2(LocalDateTime.now()));
//        System.err.println(dateTimeConvertToString3(LocalDateTime.now()));
//
//        LocalDateTime ldt = stringConvertToDateTime("2020-07-15 21:48:47");
//        System.err.println(ldt);
//        LocalDateTime ldt1 = stringConvertToDateTime1("2020年07月15日 21:48:47");
//        System.err.println(ldt1);
//        LocalDate ldt2 = stringConvertToDateTime2("2020-07-15");
//        System.err.println(ldt2);
//        LocalDate ldt3 = stringConvertToDateTime3("2020年07月15日");
//        System.err.println(ldt3);
//        System.err.println(localDateConvertToLocalDateTime(ldt2));
        LocalDateTime localDateTime = LocalDateTime
                .of(1997, 12, 21, 0, 7);

        Period period = calculationTime(localDateTime, LocalDateTime.now());


    }
}
