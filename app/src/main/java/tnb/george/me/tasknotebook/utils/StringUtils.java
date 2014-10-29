package tnb.george.me.tasknotebook.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by GeorgeZou on 2014/10/29.\
 *
 * @Description:<br/>
 * @Author:GeorgeZou(Zousongqi0213@gmail.com)<br/>
 * @Since:2014/10/29<br/>
 */
public class StringUtils {

    public final static SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

    public static boolean isEmpty(String str){
        return str == null && str.trim().equals("");
    }

    public static Date stringToDate(String dateStr) throws ParseException{
        return formatDate.parse(dateStr);
    }

    public static Date stringToDate(String dateStr,String formatStr) throws ParseException{
        DateFormat dd=new SimpleDateFormat(formatStr);
        Date date= dd.parse(dateStr);
        return date;
    }
    public static String dateConvertString(Date str){
        return formatDate.format(str);
    }
}