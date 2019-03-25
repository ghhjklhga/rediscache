package com.ways.test.rediscache.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: HinsWu
 * @Date: 2019/3/25 15:25
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String name;

    public User(){}

    public User(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}
