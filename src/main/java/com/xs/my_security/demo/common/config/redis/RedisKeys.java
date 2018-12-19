package com.xs.my_security.demo.common.config.redis;

/**
 * <p>
 *
 * </p>
 *
 * @author XieShuang
 * @version v1.0
 * @since 2018-12-01
 */
public class RedisKeys {

    /**
     * all resource key
     * @param path
     * @return
     */
    public static String getResourceKey(String path){
        return "resource:key:"+path;
    }

    /**
     * token key
     * @param token
     * @return
     */
    public static String getTokenKey(String token){
        return "token:key:"+token;
    }

    /**
     * token key
     * @param username
     * @return
     */
    public static String getUserTokenKey(String username){
        return "token:user:"+username;
    }

    /**
     * user resource key
     * @param username
     * @return
     */
    public static String getUserResourceKey(String username){
        return "resource:user:"+username;
    }

    /**
     * user 登录错误次数 key
     * @param username
     * @return
     */
    public static String getUserLoginErrorCountKey(String username){
        return "login:error:count:"+username;
    }

    /**
     * user 多少分钟之后能够登录 key
     * @param username
     * @return
     */
    public static String getUserLoginErrorTimeKey(String username){
        return "login:error:time:"+username;
    }

    /**
     * user 指定ip访问的次数 key
     * @param ip
     * @return
     */
    public static String getVisitIpKey(String ip){
        return "visit:ip:count:"+ip;
    }
}
