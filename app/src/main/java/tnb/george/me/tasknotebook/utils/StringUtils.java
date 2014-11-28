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

    public final static String DATE_TIME_FORMATE = "yyyy-MM-dd hh:mm";

    public final static SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

    public static Date stringToDate(String dateStr) throws ParseException{
        return formatDate.parse(dateStr);
    }

    public static Date stringToDate(String dateStr,String formatStr){
        DateFormat dd = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = dd.parse(dateStr);
        }catch(ParseException ex){
            ex.printStackTrace();
        }
        return date;
    }
    public static String dateConvertString(Date str){
        return formatDate.format(str);
    }

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }
}
