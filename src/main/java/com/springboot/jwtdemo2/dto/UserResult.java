package com.springboot.jwtdemo2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 用户查询结果的封装
 * @Author 傅琦
 * @Date 2019/4/12 22:56
 * @Version V1.0
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserResult<T> {
    private String state;
    private T data;
}
