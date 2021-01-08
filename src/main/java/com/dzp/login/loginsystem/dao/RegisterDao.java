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
public interface RegisterDao extends JpaRepository<UserEntity,Integer> {

    public static final String DAO_BEAN_NAME = "registerDao";

    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);

    UserEntity findByPhonenumber(String phoneNumber);

    UserEntity findByCaptcha(String captcha);

//    /**
//     * 将用户注册信息存入到数据库中(手机号注册)
//     */
//    @Modifying
//    @Query(value = "update UserEntity as us set us.username=?1,us.password=?2,us.phonenumber=?3")
//    void registerUserByPhone(String username,String password,String phonenumber);
//
//    /**
//     * 将用户注册信息存入到数据库中(邮箱注册)
//     */
//    @Modifying
//    @Query(value = "update UserEntity as us set us.username=?1,us.password=?2,us.email=?3")
//    void registerUserByEmail(String username,String password,String email);


}
