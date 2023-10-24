package com.geekaca.mall.config;

import com.auth0.jwt.interfaces.Claim;
import com.geekaca.mall.common.Constants;
import com.geekaca.mall.exceptions.NotLoginException;
import com.geekaca.mall.utils.JedisPoolUtil;
import com.geekaca.mall.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * token 拦截器
 * 统一对用户的请求进行校验
 *
 * 管理员的界面要保护
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
//    进入controller层之前拦截请求
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String token = request.getHeader("token");
//        if (token == null || StringUtils.isEmpty(token)) {
//            throw new NotLoginException(Constants.NO_LOGIN, "未携带token");
//        }
//        Map<String, Claim> stringClaimMap = JwtUtil.verifyToken(token);
//        Claim idClaim = stringClaimMap.get("id");
//        String uid = idClaim.asString();
//        Jedis jedis = JedisPoolUtil.getJedis();
//        //管理员和普通用户
//        String redisAdminToken = jedis.get("uid:admin:" + uid);
//        String redisUserToken = jedis.get("uid:user:" + uid);
//        if (redisAdminToken == null && redisUserToken == null){
//            //说明token过期，或者被删除了token
//            throw new NotLoginException(Constants.NO_LOGIN, "token超时");
//        }
//        return true;
//    }
//    //访问controller之后 访问视图之前被调用
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//    }
//    //访问视图之后被调用
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
}
