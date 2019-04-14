package com.springboot.jwtdemo2.service.serviceimpl;

import com.springboot.jwtdemo2.mapper.UserMapper;
import com.springboot.jwtdemo2.pojo.User;
import com.springboot.jwtdemo2.service.UserService;
import com.springboot.jwtdemo2.dto.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户服务层接口的实现
 * @Author 傅琦
 * @Date 2019/4/12 22:51
 * @Version V1.0
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResult<User> findByUsername(String username) {
        User user = userMapper.findByUsername(username);
        if (user == null){
            return new UserResult<>("fail", null);
        }else {
            return new UserResult<>("success", user);
        }
    }

    @Override
    public UserResult<User> findById(int userId) {
        User user = userMapper.findById(userId);
        if (user == null){
            return new UserResult<>("fail", null);
        }else {
            return new UserResult<>("success", user);
        }
    }
}
