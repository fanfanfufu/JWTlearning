package com.springboot.jwtdemo2.mapper;

import com.springboot.jwtdemo2.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Description: UserMapper的测试类
 * @Team 电子科技大学自动化研究所
 * @Author 傅琦
 * @Date 2019/4/12 22:00
 * @Version V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testMapper(){
        assert userMapper != null;
    }

    @Test
    public void findByUsername() {
        String name = "李雷";
        User user = userMapper.findByUsername(name);
        if (user == null){
            System.out.println("该用户不存在");
        }else {
            System.out.println("the user is: " + user.getUsername());
        }
    }

    @Test
    public void findById() {
        User user = userMapper.findById(2);
        if (user == null){
            System.out.println("该用户不存在");
        }else {
            System.out.println("the user is: " + user.getUsername());
        }
    }
}