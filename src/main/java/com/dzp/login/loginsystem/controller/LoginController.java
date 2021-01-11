package com.dzp.login.loginsystem.controller;

import com.dzp.login.loginsystem.api.CommonResult;
import com.dzp.login.loginsystem.entity.UserEntity;
import com.dzp.login.loginsystem.serviceIml.LoginServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * <>请求接口</>
 *
 * @Author: dongzhanpeng
 * @Date: 2020-12-21 13:45
 * @Version 1.0
 */
@RestController
public class LoginController {


    @Autowired
    private LoginServiceIml loginServiceIml;

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody UserEntity user) {

        String userName = user.getUsername();
        String password = user.getPassword();
        CommonResult<String> commonResult = loginServiceIml.findUserNameAndPassword(userName, password);
        return commonResult;
    }

}
