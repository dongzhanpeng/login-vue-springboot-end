package com.dzp.login.loginsystem.controller;

import com.dzp.login.loginsystem.api.CommonResult;
import com.dzp.login.loginsystem.entity.RegisterUserEntiy;
import com.dzp.login.loginsystem.entity.UserEntity;
import com.dzp.login.loginsystem.serviceIml.RegisterServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: dongzhanpeng
 * @Date: 2021-1-4 16:22
 * @Version 1.0
 */
@RestController
public class RegisterController {

    @Autowired
    private RegisterServiceIml registerServiceIml;

    @RequestMapping(value = "/admin/register", method = RequestMethod.POST)
    public CommonResult doRegister(@RequestBody RegisterUserEntiy registerUserEntiy){
        CommonResult commonResult = registerServiceIml.doRegister(registerUserEntiy);
        return commonResult;
    }
}
