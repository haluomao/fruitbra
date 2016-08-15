package com.money.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mfg on 16/08/02.
 */
public class NamingUtil {
    public static String getDateName(Date date, String format){
        SimpleDateFormat sdf =   new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String getDateName(Date date){
        SimpleDateFormat sdf =   new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }
    public static String getDateLongName(){
        return String.valueOf(System.currentTimeMillis());
    }
}
