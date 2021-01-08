package com.dzp.login.loginsystem.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

/**
 * @Author: dongzhanpeng
 * @Date: 2020-12-21 13:45
 * @Version 1.0
 */
@Entity
@Table(name = "user")
@Getter
@Setter
@Component
public class UserEntity {

    public static final String ENTITY_BEAN_NAME = "userEntity";

    @Id
    @Column(name = "id", length = 20)
    private int id;     //用户唯一标识
    @Column(name = "username", length = 100)
    private String username;        //用户名
    @Column(name = "password", length = 100)
    private String password;        //密码
    @Column(name = "email", length = 100)
    private String email;       //邮箱
    @Column(name = "captcha", length = 100)
    private String captcha;     //验证码
    @Column(name = "phonenumber", length = 100)
    private String phonenumber;     //手机号
}
