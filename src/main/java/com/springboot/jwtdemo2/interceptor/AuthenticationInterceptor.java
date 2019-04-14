package com.springboot.jwtdemo2.interceptor;

import com.springboot.jwtdemo2.dto.UserResult;
import com.springboot.jwtdemo2.pojo.User;
import com.springboot.jwtdemo2.service.UserService;
import com.springboot.jwtdemo2.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 自定义的拦截器，用于对tocken以及其中所包含的信息进行校验
 * @Author 傅琦
 * @Date 2019/4/13 19:56
 * @Version V1.0
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法，则直接通过
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        // 从请求头中获取tocken
        String tocken = request.getHeader("tocken");
//        System.out.println(tocken);
        // 当没有获取到tocken时的处理
        if (tocken == null){
            response.sendError(403, "无tocken，请先登录");
            return false;
        }else {
            try {
                // tocken还未过期时的处理
                // 获取声明部分
                Claims claims = JwtUtil.parseTocken(tocken);
                String username = claims.get("username", String.class);
                // userService导入存在问题时的处理
                if (userService == null){
                    System.out.println("无法导入userService。");
                    response.sendError(500, "服务器发生错误");
                    return false;
                }
                // 查询用户信息
                UserResult<User> userResult = userService.findByUsername(username);
                if ("success".equals(userResult.getState())){
                    return true;
                }else {
                    response.sendError(401, "用户不存在，请先注册");
                    return false;
                }
            }catch (ExpiredJwtException exp){
                // tocken过期时的处理
                response.sendError(40102, "tocken过期，请重新登录");
                return false;
            }
        }
//        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("处理器完成后的方法，此时说明控制器已经执行完毕。");
    }
}
