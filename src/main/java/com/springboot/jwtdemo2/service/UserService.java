package com.springboot.jwtdemo2.service;

import com.springboot.jwtdemo2.pojo.User;
import com.springboot.jwtdemo2.dto.UserResult;

/**
 * @Description: 用户服务层接口
 * @Author 傅琦
 * @Date 2019/4/12 22:46
 * @Version V1.0
 */
public interface UserService {
    /**
     * 根据用户名查询用户
     * @param username
     * @return User
     */
    UserResult<User> findByUsername(String username);

    /**
     * 根据用户id查询用户
     * @param userId
     * @return User
     */
    UserResult<User> findById(int userId);
}
