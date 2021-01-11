package com.dzp.login.loginsystem.service;

import com.dzp.login.loginsystem.api.CommonResult;
import com.dzp.login.loginsystem.dao.LoginDao;
import com.dzp.login.loginsystem.entity.UserEntity;
import com.dzp.login.loginsystem.serviceIml.LoginServiceIml;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Objects;

/**
 * @Author: dongzhanpeng
 * @Date: 2020-12-21 16:40
 * @Version 1.0
 */
@Service
public class LoginService implements LoginServiceIml {

    @Qualifier("loginDao")
    @Autowired
    private LoginDao loginDao;

    /**
     * 登录Service
     *
     * @param userName
     * @param password
     * @return
     */
    @Override
    public CommonResult<String> findUserNameAndPassword(String userName, String password) {
        //调用dao层
        UserEntity userEntity = loginDao.findByUsername(userName);
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //判断是否存在这条记录
        if (ObjectUtils.isEmpty(userEntity)) {
            return CommonResult.validateFailed();
        } else if (Objects.equals(userName, userEntity.getUsername()) && !Objects.equals(password, userEntity.getPassword())) {       //判断密码是否正确
            return CommonResult.failed("密码错误，请重新输入");
        } else if (Objects.equals(userName, userEntity.getUsername()) && Objects.equals(password, userEntity.getPassword())) {      //判断是否是该用户
            return CommonResult.success(userName);
        } else {
            return CommonResult.failed("用户名密码错误，请重新输入");
        }
    }
}
