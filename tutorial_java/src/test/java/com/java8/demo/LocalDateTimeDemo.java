package com.java8.demo;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @author jimmy
 * @date 2018/8/2221:02
 */
public class LocalDateTimeDemo {
    /**
     * LocalDate
     * LocalTime
     * LocalDateTime
     */
    @Test
    public void localDateTimeTest(){
        //获取当前时间
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
    }

    /**
     * Instant 时间戳
     */
    @Test
    public void instantTest(){
        Instant instant = Instant.now();//默认获取UTC时间
        System.out.println(instant);//2018-08-22T13:15:12.978Z


        OffsetDateTime oft = instant.atOffset(ZoneOffset.ofHours(8));//加8小时
        System.out.println(oft);//2018-08-22T21:17:18.436+08:00

        //毫秒值
        System.out.println(instant.toEpochMilli());
    }

    /**
     * Duration 计算两个时间之间的间隔
     * Period   计算两个日期之间的间隔
     */
    @Test
    public void betweenTest(){

    }


    /**
     * TemporalAdjuster 时间校正器
     * TemporalAdjusters 封装了常用类
     */
    @Test
    public void temporalAdjusterTest(){
        LocalDateTime ldt1 = LocalDateTime.now();
        //下个周日的日期
        LocalDateTime ldt2 = ldt1.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt2);
    }

    /**
     * DateTimeFormatter
     */
    @Test
    public void dateTimeFormatterTest(){
        DateTimeFormatter dtf1 = DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt.format(dtf1));

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String str = dtf2.format(ldt);
        System.out.println(str);

        LocalDateTime ldt2 = ldt.parse(str,dtf2);
    }
}
