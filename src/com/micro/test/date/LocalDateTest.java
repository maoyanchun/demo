package com.micro.test.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.time.*;
import java.time.temporal.ChronoUnit;

import java.util.Calendar;
import java.util.Date;

/**
 * Description:
 * java.time.LocalDate (jdk1.8)
 * <p>
 * Created by mycge at 23:53 on 2019-09-28.
 */
public class LocalDateTest {
    /**
     * Date formats are not synchronized.
     * If multiple threads access a format concurrently, it must be synchronized
     */
//    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * static定义-多线程安全
     */
    private static final ThreadLocal<DateFormat> THREAD_LOCAL = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static void main(String[] args) {
//        function1();

        /*JDK8*/
//        function2();
        function3();
    }

    private static void function3() {
//        System.out.println(System.currentTimeMillis());

        Clock clock = Clock.systemUTC();
        System.out.println("clock: " + clock);
        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("defaultClock: " + defaultClock);

        LocalDate now = LocalDate.now();
        LocalDate tomorrow = LocalDate.of(2019, 10, 1);
        if (tomorrow.isAfter(now)) {
            System.out.println("tomorrow comes after today...");
        }
        LocalDate yesterday = now.minus(1, ChronoUnit.DAYS);
        if (yesterday.isBefore(now)) {
            System.out.println("yesterday is day before today...");
        }

        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localtDateAndTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america);
        System.out.println("Current datetime in a particular timezone : " + dateAndTimeInNewYork);

        YearMonth yearMonth = YearMonth.now();
        System.out.println(String.format("yearMonth: %s, days: %s", yearMonth, yearMonth.lengthOfMonth()));
        YearMonth creditCardExpiry = YearMonth.of(2019, Month.DECEMBER);
        System.out.println("creditCardExpiry: " + creditCardExpiry);

        /*闰年*/
        if (LocalDate.of(2024, 1, 2).isLeapYear()) {
            System.out.println("This year is Leap Year");
        } else {
            System.out.println("This year is not Leap Year");
        }

//        示例 15、计算两个日期之间的天数和月数  TODO...



    }

    private static void function2() {
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate=" + localDate);

        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        System.out.println(String.format("year=%s, month=%s, day=%s", year, month, day));

        LocalDate localDate1 = LocalDate.of(2019, 9, 29);
        System.out.println("localDate1=" + localDate1);

        if (localDate.equals(localDate1)) {
            System.out.println("same...");
        }

        /*检查像生日这种周期性事件*/
        MonthDay birthday = MonthDay.of(localDate1.getMonth(), localDate1.getDayOfMonth());
        MonthDay today = MonthDay.from(LocalDate.now());
        if (birthday.equals(today)) {
            System.out.println("today is your birthday...");
        }

        /************************************************************************/
        LocalTime localTime = LocalTime.now();
        System.out.println("localTime=" + localTime);
        LocalTime localTime1 = localTime.plusHours(2).plusMinutes(2).plusSeconds(20);
        System.out.println("localTime1=" + localTime1);

        LocalDate localDate2 = localDate.plus(1, ChronoUnit.WEEKS);
        System.out.println("a week later localDate2: " + localDate2);

        LocalDate localDate3 = localDate.minus(1, ChronoUnit.YEARS);
        LocalDate localDate4 = localDate.plus(1, ChronoUnit.YEARS);
        System.out.println("a year before localDate3: " + localDate3);
        System.out.println("a year after localDate4: " + localDate4);


        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime=" + localDateTime);
    }

    private static void function1() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        cal.add(Calendar.MONTH, -1);
        cal.add(Calendar.DAY_OF_MONTH, 4);
        Date date = cal.getTime();
        System.out.println(THREAD_LOCAL.get().format(date));
    }

}
