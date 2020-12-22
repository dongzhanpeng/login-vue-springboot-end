package com.dzp.login.loginsystem.service;

import com.dzp.login.loginsystem.api.CommonResult;
import com.dzp.login.loginsystem.dao.LoginDao;
import com.dzp.login.loginsystem.entity.UserEntity;
import com.dzp.login.loginsystem.serviceIml.LoginServiceIml;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

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

    @Override
    public CommonResult<String> findUserNameAndPassword(String userName, String password) {
        //调用dao层
        UserEntity userEntity = loginDao.findByUsernameAndPassword(userName, password);
//        if (ObjectUtils.isEmpty(userEntity)) {
        if (userEntity == null) {
            return CommonResult.validateFailed();
        } else if (userEntity.getUsername().equals("admin") && userEntity.getPassword().equals("admin")) {
            return CommonResult.success("admin");
        } else {
            return CommonResult.validateFailed();
        }
    }
}
