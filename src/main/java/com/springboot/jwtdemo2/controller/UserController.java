package com.springboot.jwtdemo2.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.jwtdemo2.dto.UserResult;
import com.springboot.jwtdemo2.pojo.User;
import com.springboot.jwtdemo2.service.UserService;
import com.springboot.jwtdemo2.utils.JwtUtil;
import com.springboot.jwtdemo2.vo.UserViewObject;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 用户控制器
 * @Author 傅琦
 * @Date 2019/4/13 15:21
 * @Version V1.0
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserViewObject<Object> userLongin(@RequestBody User user){
        UserViewObject<Object> result = new UserViewObject<>();
        UserResult<User> userResult = userService.findByUsername(user.getUsername());
        if ("fail".equals(userResult.getState())){
            result.setState("fail");
            result.setData("用户不存在，请先注册");
        }else {
            User user1 = userResult.getData();
            if (!user.getPassword().equals(user1.getPassword())){
                result.setState("fail");
                result.setData("密码错误，请重新输入");
            }else {
                String tocken = JwtUtil.generalTocken(user1);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("user", user1);
                jsonObject.put("tocken", tocken);
                result.setState("sucees");
                result.setData(jsonObject);
            }
        }
        return result;
    }

    @PostMapping("/whoami")
    public String getMessage(HttpServletRequest httpServletRequest) {
        try {
            String tocken = httpServletRequest.getHeader("tocken");
            Claims claims = JwtUtil.parseTocken(tocken);
            String username = claims.get("username", String.class);
            System.out.println("执行处理器中的控制器中的对应方法中的方法体。");
            return username + "，你已通过验证";
        } catch (Exception e) {
            e.printStackTrace();
            return "处理tocken出现错误。";
        }
    }
}
