package com.dzp.login.loginsystem.dao;

import com.dzp.login.loginsystem.entity.RegisterUserEntiy;
import com.dzp.login.loginsystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Author: dongzhanpeng
 * @Date: 2021-1-4 16:22
 * @Version 1.0
 */
@Repository(RegisterDao.DAO_BEAN_NAME)
public interface RegisterDao extends JpaRepository<UserEntity, Integer> {

    public static final String DAO_BEAN_NAME = "registerDao";

    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);

    UserEntity findByPhonenumber(String phoneNumber);

    UserEntity findByCaptcha(String captcha);
}
