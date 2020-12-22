package com.dzp.login.loginsystem.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: dongzhanpeng
 * @Date: 2020-12-21 13:45
 * @Version 1.0
 */
@Entity
@Table(name = "user")
@Getter
@Setter
public class UserEntity {
    @Id
    @Column(name = "id",length = 20)
    private int id;
    @Column(name = "username",length = 100)
    private String username;
    @Column(name = "password",length = 100)
    private String password;
}
