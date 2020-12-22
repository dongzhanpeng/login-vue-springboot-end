package com.dzp.login.loginsystem.dao;

import com.dzp.login.loginsystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: dongzhanpeng
 * @Date: 2020-12-21 16:05
 * @Version 1.0
 */
@Repository(LoginDao.DAO_BEAN_NAME)
public interface LoginDao extends JpaRepository<UserEntity,Integer> {

    /**
     * Spring注册时的名称.
     */
    public static final String DAO_BEAN_NAME = "loginDao";

    UserEntity findByUsernameAndPassword(String userName,String password);
}
