package com.course.jwt.coursejwt.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Component
public class DateUtils {
    @Value("$(jms.jwt.timezone)")
    private String TIMEZONE;

    private SimpleDateFormat simpleDateFormat(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
        return sdf;
    }

    public String getDateString(){
        Date now = new Date();
        return simpleDateFormat().format(now);
    }

    public Long getDateMillis(){
        Date now = new Date();
        String strDate= simpleDateFormat().format(now);
        Date strNow = new Date();

        try {
            strNow = simpleDateFormat().parse(strDate);
        } catch (ParseException e) {}
        return strNow.getTime();
    }
}
