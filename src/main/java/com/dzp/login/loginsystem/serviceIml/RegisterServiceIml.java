package com.dzp.login.loginsystem.serviceIml;

import com.dzp.login.loginsystem.api.CommonResult;
import com.dzp.login.loginsystem.entity.RegisterUserEntiy;
import com.dzp.login.loginsystem.entity.UserEntity;

public interface RegisterServiceIml {

    public static final String SERVICE_BEAN_NAME = "registerServiceIml";

    CommonResult doRegister(RegisterUserEntiy registerUserEntiy);
}
