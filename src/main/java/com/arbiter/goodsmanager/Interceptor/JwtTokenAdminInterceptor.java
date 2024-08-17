package com.arbiter.goodsmanager.Interceptor;

import com.arbiter.goodsmanager.properties.JwtProperties;
import com.arbiter.goodsmanager.service.user.UserService;
import com.arbiter.goodsmanager.util.JwtUtil;
import com.arbiter.goodsmanager.util.ThreadLocalUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
@AllArgsConstructor
public class JwtTokenAdminInterceptor implements HandlerInterceptor {
    private final UserService userService;
    private final JwtProperties jwtProperties;

    /**
     * 校验jwt
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        //1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        //2、校验令牌
        try {
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Integer userId = (Integer) claims.get("id");
            System.out.println("id: " + userId.intValue());
            log.info("当前员工id：" + userId);
            // 通过ThreadLocal存储当前用户id
            ThreadLocalUtil.addCurrentUser(userService.getById(userId));

        } catch (JwtException exception) {
            throw new JwtException("令牌不正确");
        }
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }
}
