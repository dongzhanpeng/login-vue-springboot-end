package com.dzp.login.loginsystem.dao;

import com.dzp.login.loginsystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository(ForgetPasswordDao.DAO_BEAN_NAME)
public interface ForgetPasswordDao extends JpaRepository<UserEntity,Integer> {

    /**
     * Spring注册时的名称
     */
    public static final String DAO_BEAN_NAME = "forgetPasswordDao";



    /**
     * 根据email查询
     * @param email
     * @return
     */
    UserEntity  findByEmail(String email);

    /**
     * 将验证码存入到数据库中根据邮箱号
     */
    @Modifying
    @Query(value = "update UserEntity ue set ue.captcha=?1 where ue.email=?2")
    void updateCaptchaByEmail(String captcha,String email);

    UserEntity findByPhonenumber(String phoneNumber);
}
