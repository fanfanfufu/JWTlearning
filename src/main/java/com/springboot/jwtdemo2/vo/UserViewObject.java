package com.springboot.jwtdemo2.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 用户信息视图层对象
 * @Author 傅琦
 * @Date 2019/4/13 16:19
 * @Version V1.0
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserViewObject<T> {
    private String state;
    private T data;
}
