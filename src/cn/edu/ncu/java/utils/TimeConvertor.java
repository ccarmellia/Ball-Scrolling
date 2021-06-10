package cn.edu.ncu.java.utils;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeConvertor {
    public static final SimpleDateFormat _YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final Data parse_YMDHMS(String rq) throws Exception{
        if(rq == null){
            return null;
        }
        try{
            return (Data) _YMDHMS.parse(rq);
        }catch (ParseException e){
            System.out.println("[字符串转换时间异常]："+e.getMessage());
            throw new Exception("字符串转换时间异常",e);
        }
    }
}
