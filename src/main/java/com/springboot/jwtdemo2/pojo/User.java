package com.springboot.jwtdemo2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 用户实体类，使用Lombok相关注解来实现构造器和getter、setter
 * @Author 傅琦
 * @Date 2019/4/12 21:30
 * @Version V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * 用户id
     */
    private int userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
}
