package com.xs.my_security.demo.common.util;

/**
 * <p>
 *
 * </p>
 *
 * @author XieShuang
 * @version v1.0
 * @since 2018-12-18
 */
public class TimeUtil {

    /**
     * 将秒数转换为日时分秒
     * @param second
     * @return
     */
    public static String second2Chinese(long second){
        long days = second / 86400;            //转换天数
        second = second % 86400;            //剩余秒数
        long hours = second / 3600;            //转换小时
        second = second % 3600;                //剩余秒数
        long minutes = second /60;            //转换分钟
        second = second % 60;                //剩余秒数
        if(days>0){
            return days + "天" + hours + "小时" + minutes + "分" + second + "秒";
        }else if (hours>0){
            return hours + "小时" + minutes + "分" + second + "秒";
        }else if (minutes>0){
            return minutes + "分" + second + "秒";
        }else {
            return second + "秒";
        }
    }
}
