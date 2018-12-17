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
}
