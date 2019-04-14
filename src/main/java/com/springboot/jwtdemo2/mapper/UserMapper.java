package com.springboot.jwtdemo2.mapper;

import com.springboot.jwtdemo2.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 用户数据持久层接口，其实现为userMapper.xml文件
 * @Author 傅琦
 * @Date 2019/4/12 21:39
 * @Version V1.0
 */
@Mapper
public interface UserMapper {
    /**
     * 根据用户姓名进行查找
     * @param username
     * @return User
     */
    User findByUsername(@Param("username") String username);

    /**
     * 根据用户id进行查找
     * @param userId
     * @return User
     */
    User findById(@Param("userId") int userId);
}
