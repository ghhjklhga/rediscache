package com.ways.test.rediscache.dao;

import com.ways.test.rediscache.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @Author: HinsWu
 * @Date: 2019/3/25 16:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserDaoTest {
    @Autowired
    UserDao userDao;

    @Test
    public void test() throws InterruptedException {
        User user = userDao.find6(6);
        log.info("find6执行完成，结果:{}",user.getId());
        log.info("【main函数执行1】返回结果：{}",userDao.find1(user));
        log.info("【main函数执行2】返回结果：{}",userDao.find2(user.getId()));
        log.info("【main函数执行update】返回结果：{}",userDao.updateUser(user));
        log.info("【main函数执行3】返回结果：{}",userDao.find3(user.getId()));
        log.info("【main函数执行4】返回结果：{}",userDao.find4(user));
        userDao.reload();
        log.info("【main函数执行5】返回结果：{}",userDao.find5(user));


        log.info("全部方法执行完成！");
    }

    @Test
    public Date getTomorrow(){
        Date date = null;
        try {
            Thread.sleep(24 * 60 * 60 * 1000);
            date = new Date();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return date;
    }
}