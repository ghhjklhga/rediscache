package com.ways.test.rediscache.dao;

import com.ways.test.rediscache.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * @Author: HinsWu
 * @Date: 2019/3/25 15:25
 */
@Service
@Slf4j
public class UserDao {
    // 如果要用固定字符串加上参数的属性记得加单引号
    @Cacheable(value = "users", key = "'helloworld'+#p0.id")
    public User find1(User user) {
        log.info("执行find1方法");
        return new User(1, "内部新建");
    }
    @Cacheable(value = "users", key = "#id")
    public User find2(Integer id) {
        log.info("执行find2方法");
        return new User(2, "内部新建");
    }

    @Cacheable(value = "users", key = "#p0")
    public User find3(Integer id) {
        log.info("执行find3方法");
        return new User(3, "内部新建");
    }

    @Cacheable(value = "users", key = "#user.id")
    public User find4(User user) {
        log.info("执行find4方法");
        return new User(4, "内部新建");
    }

    @Cacheable(value = "users", key = "#p0.id")
    public User find5(User user) {
        log.info("执行find5方法");
        return new User(5, "内部新建");
    }

    @CacheEvict(value="users",key="#user.getId()")// 清空accountCache 缓存
    public User updateUser(User user) {
        return new User(7, "新建updateUser");
    }

    @CacheEvict(value="users",allEntries=true)// 清空accountCache 缓存
    public void reload() {
    }

    @CachePut(value = "users", key = "#p0")//每次都会执行方法，并将结果存入指定的缓存中
    public User find6(Integer id) {
        log.info("执行find6方法");
        return new User(id,"find6中新建");
    }

    @Caching(put = {
            @CachePut(value = "user", key = "#user.id"),
            @CachePut(value = "user", key = "#user.username"),
            @CachePut(value = "user", key = "#user.email")
    })
    public User save(User user) { return null;}


    //@Cacheable 在执行方法之前判断condition，如果返回true，则查缓存；
    @Cacheable(value = "user", key = "#id", condition = "#id lt 10")
    public User conditionFindById(final Long id) { return null;}

    //@CachePut 在执行完方法后判断condition，如果返回true，则放入缓存；
    @CachePut(value = "user", key = "#id", condition = "#result.username ne 'zhang'")
    public User conditionSave(final User user)   { return null;}

    //@CachePut将在执行完方法后判断unless，如果返回false，则放入缓存；（即跟condition相反）
    @CachePut(value = "user", key = "#user.id", unless = "#result.username eq 'zhang'")
    public User conditionSave2(final User user)   { return null;}

    //@CacheEvict， beforeInvocation=false表示在方法执行之后调用,判断condition，如果返回true，则移除缓存；
    @CacheEvict(value = "user", key = "#user.id", beforeInvocation = false, condition = "#result.username ne 'zhang'")
    public User conditionDelete(final User user)  { return null;}

}
