package com.example.leetcode.solutions;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 * @author renxinheng
 * @ClassName DayOfTheWeek
 * @createTime 2024/1/11
 * @desc 1185. 一周中的第几天
 * 给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。
 * 输入为三个整数：day、month 和 year，分别表示日、月、年。
 * 您返回的结果必须是这几个值中的一个 {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}。
 * <p>
 * 示例 1：
 * 输入：day = 31, month = 8, year = 2019
 * 输出："Saturday"
 * <p>
 * 示例 2：
 * 输入：day = 18, month = 7, year = 1999
 * 输出："Sunday"
 * <p>
 * 示例 3：
 * 输入：day = 15, month = 8, year = 1993
 * 输出："Sunday"
 * <p>
 * 提示：
 * 给出的日期一定是在 1971 到 2100 年之间的有效日期。
 */
public class DayOfTheWeek {

    public static final String[] WEEK_DAY = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public static final int startDayOfWeekIdx = 3;
    public static final int endDayOfWeekIdx = 4;

    public static final int START_YEAR = 1970;
    public static final int START_MONTH = 12;
    public static final int START_DAY = 28;

    public static void main(String[] args) {
        int day = 31, month = 8, year = 2019;
        System.out.println(dayOfTheWeek(day, month, year));
        System.out.println(dayOfTheWeek2(day, month, year));

//        for (DayOfWeek value : DayOfWeek.values()) {
//            System.out.print("\"" + value.name().charAt(0) + value.name().substring(1).toLowerCase() + "\",");
//        }
//        System.out.println();
//
//        System.out.println(getMonthDayCount(2023, 2));
//        System.out.println(getMonthDayCount(2024, 2));
//
//        LocalDate startDay = LocalDate.of(1970, 1, 1);
//        LocalDate endDay = LocalDate.of(2100, 1, 1);
//
//        System.out.println(startDay.getDayOfWeek());
//        System.out.println(endDay.getDayOfWeek());
//
//        LocalDateTime today = LocalDateTime.now();
//        System.out.println(Duration.between(LocalDateTime.of(1970, 1, 1, 0, 0, 0), today).toDays());
//        System.out.println(getDurationDates(today.getYear(), today.getMonthValue(), today.getDayOfMonth()));

    }

    /**
     * 手动实现
     *
     * @param day
     * @param month
     * @param year
     * @return
     */
    public static String dayOfTheWeek2(int day, int month, int year) {
        int dates = getDurationDates(START_YEAR, START_MONTH, START_DAY, year, month, day);
//        System.out.println("duration: " +
//                Duration.between(LocalDateTime.of(1970, 12, 28, 0, 0, 0), LocalDateTime.of(year, month, date, 0, 0, 0)).toDays());
        System.out.println("getDurationDates: " + dates);
        return WEEK_DAY[dates % 7];
    }

    /**
     * 普通闰年是每隔4年,世界闰年的判断规则(2100年之内的范围)是:
     * - 如果一个年份能被4整除，但不能被100整除，那么它是闰年。
     * - 如果一个年份能被400整除，那么它也是闰年。
     *
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    public static int getMonthDayCount(int year, int month) {
        return month == 2 ? isLeapYear(year) ? 29 : 28 :
                (month == 4 || month == 6 || month == 7 || month == 9 || month == 11) ?
                        30 : 31;
    }

    public static int getYearDayCount(int year) {
        return isLeapYear(year) ? 366 : 365;
    }

    /**
     * 计算日期差距,
     * 先算整年的,再算整月的
     *
     * @param year
     * @param month
     * @param date
     * @return
     */
    public static int getDurationDates(int startYear, int startMonth, int startDate, int year, int month, int date) {
        boolean negative = false;
        if (startYear > year ||
                (startYear == year &&
                        (startMonth > month ||
                                (startMonth == month &&
                                        startDate > date)))) {
            negative = true;
            //互换
            int tempY = startYear, tempM = startMonth, tempD = startDate;
            startYear = year;
            startMonth = month;
            startDate = date;
            year = tempY;
            month = tempM;
            date = tempD;
        }
        int totalDays = 0;
        if (startYear < year) {
            //日期月份归整
            int maxMonthDay = getMonthDayCount(startYear, startMonth);

            while (startDate < maxMonthDay) {

                startDate++;
            }
        }
        while (startYear < year - 1) {
            totalDays += getYearDayCount(startYear);
            startYear++;
        }
        //先算年数 然后算差值
        if (startYear != year) {
            while (startMonth != month - 1) {
                totalDays += getMonthDayCount(startYear, startMonth);
                startMonth++;
                if (startMonth == 13) {
                    startMonth = 1;
                    startYear++;
                }
            }
        }

        int maxMonthDay = getMonthDayCount(startYear, startMonth);
        while (startDate != date) {
            totalDays++;
            startDate++;
            if (startDate == maxMonthDay) {
                startDate = 1;
                startMonth++;
            }
        }
        return negative ? -1 * totalDays : totalDays;
    }


    /**
     * 用API的情况1
     *
     * @param day
     * @param month
     * @param year
     * @return
     */
    public static String dayOfTheWeek(int day, int month, int year) {
        String dayOfWeek = LocalDate.of(year, month, day).getDayOfWeek().name().toLowerCase();
        return dayOfWeek.charAt(0) + dayOfWeek.substring(1).toLowerCase();
    }

    /**
     * 用API的情况1
     *
     * @param day
     * @param month
     * @param year
     * @return
     */
    public static String dayOfTheWeek1(int day, int month, int year) {
        Calendar instance = Calendar.getInstance();
        instance.set(year, month, day);
        return WEEK_DAY[instance.get(Calendar.DAY_OF_WEEK)];
    }
}
