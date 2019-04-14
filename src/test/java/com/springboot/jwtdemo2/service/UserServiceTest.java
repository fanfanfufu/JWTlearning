package com.springboot.jwtdemo2.service;

import com.springboot.jwtdemo2.pojo.User;
import com.springboot.jwtdemo2.dto.UserResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Team 电子科技大学自动化研究所
 * @Author 傅琦
 * @Date 2019/4/13 11:21
 * @Version V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testService(){
        assert userService != null;
    }

    @Test
    public void findByUsername() {
        UserResult<User> userResult = userService.findByUsername("李雷");
        System.out.println(userResult);
    }

    @Test
    public void findById() {
        UserResult<User> result = userService.findById(3);
        System.out.println(result);
    }
}