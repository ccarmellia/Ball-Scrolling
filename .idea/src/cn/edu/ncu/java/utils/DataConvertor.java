package cn.edu.ncu.java.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class DataConvertor {

    public static LocalDate converFromTime(Timestamp timestamp){
        return timestamp.toLocalDateTime().toLocalDate();
    }

    public static  Timestamp converFromLocal(LocalDate date){
        return new Timestamp(date.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli());
    }
}
