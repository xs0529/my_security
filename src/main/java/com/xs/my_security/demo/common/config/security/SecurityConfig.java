package com.xs.my_security.demo.common.config.security;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.xs.my_security.demo.common.bean.ResponseResult;
import com.xs.my_security.demo.common.config.redis.RedisKeys;
import com.xs.my_security.demo.common.config.redis.RedisUtils;
import com.xs.my_security.demo.common.util.NetUtil;
import com.xs.my_security.demo.entity.Resource;
import com.xs.my_security.demo.entity.User;
import com.xs.my_security.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author XieShuang
 * @version v1.0
 * @since 2018-12-01
 */
@Configuration
@Slf4j
public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtils redisUtils;

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    @Bean
    public IpInterceptor getIpInterceptor() {
        return new IpInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加限流拦截器
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(getIpInterceptor());
        interceptorRegistration.addPathPatterns("/**");
        //添加权限拦截器
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
        //排除的路径
        addInterceptor.excludePathPatterns("/error/**");
        addInterceptor.excludePathPatterns("/login/**");
        addInterceptor.excludePathPatterns("/file/**");
        addInterceptor.excludePathPatterns("/mzui/binding");
        addInterceptor.excludePathPatterns("/css/**");
        addInterceptor.excludePathPatterns("/js/**");
        addInterceptor.excludePathPatterns("/images/**");
        addInterceptor.excludePathPatterns("/wx/portal/**");
        //拦截所有路径
        addInterceptor.addPathPatterns("/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {
        /**
         * 权限验证方法，此方法返回true表示验证通过
         * @param request
         * @param response
         * @param handler
         * @return
         */
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
            String authorization = request.getHeader("Authorization");
            User user = null;
            if (StrUtil.isBlank(authorization)){
                writerResponse(response,-1,"用户未登录");
                return false;
            }else {
                try {
                    user = (User) redisUtils.get(RedisKeys.getTokenKey(authorization));
                    if (user==null){
                        log.error("根据token从redis获取用户信息为空");
                        writerResponse(response,-1,"token未认证或已过期");
                        return false;
                    }
                    if (user.getIsUsable()==0){
                        log.error("账户被锁定");
                        writerResponse(response,-1,"账户已被锁定");
                        return false;
                    }
                }catch (Exception e){
                    log.error("根据token从redis获取用户信息失败");
                    writerResponse(response,-1,"token未认证或已过期");
                    return false;
                }
            }
            String uri = request.getRequestURI();
            try {
                Resource resource = (Resource) redisUtils.get(RedisKeys.getResourceKey(uri));
                if (resource!=null){
                    switch (resource.getVerification()){
                        case 0:
                            request.setAttribute("user",user);
                            return true;
                        case 2:
                            log.error("无法访问该链接："+uri);
                            writerResponse(response,-1,"，无法访问该链接");
                            return false;
                        default:
                            Map<String,String> resourceMap = (Map<String, String>) redisUtils.get(RedisKeys.getUserResourceKey(user.getUsername()));
                            if (resourceMap==null){
                                log.error("获取用户："+user.getUsername()+" 的权限map失败");
                                writerResponse(response,-1,"获取权限信息失败");
                                return false;
                            }
                            if (StrUtil.isBlank(resourceMap.get(uri))){
                                log.error("没有权限");
                                writerResponse(response,-1,"没有权限");
                                return false;
                            }
                            request.setAttribute("user",user);
                            return true;
                    }
                }else {
                    log.error("没有获取到["+uri+"]的权限信息");
                    writerResponse(response,-2,"获取权限信息失败");
                    return false;
                }
            }catch (Exception e){
                log.error("获取权限信息失败");
                writerResponse(response,-2,"获取权限信息失败");
                return false;
            }
        }
    }

    private class IpInterceptor extends HandlerInterceptorAdapter{

        /**
         * 1秒访问超过10次拒绝访问
         * @param request
         * @param response
         * @param handler
         * @return
         */
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
            String ipAddress = NetUtil.getIpAddress(request);
            String key = RedisKeys.getVisitIpKey(ipAddress);
            Object o = redisUtils.get(key);
            if (o==null){
                redisUtils.set(key,1,1);
                return true;
            }else if ((int)o>10){
                log.error("ip:"+ipAddress+"拒绝访问");
                writerResponse(response,-200,"访问频繁，拒绝访问");
                return false;
            }else {
                redisUtils.incr(key,1);
                return true;
            }
        }
    }

    private void writerResponse(HttpServletResponse response,Integer status,String content){
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        try {
            response.getWriter().write(JSON.toJSONString(ResponseResult.builder()
                    .status(status)
                    .msg(content)
                    .build()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
