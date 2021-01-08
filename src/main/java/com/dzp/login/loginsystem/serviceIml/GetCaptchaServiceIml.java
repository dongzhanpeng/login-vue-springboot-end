package com.dzp.login.loginsystem.serviceIml;

import com.dzp.login.loginsystem.api.CommonResult;

public interface GetCaptchaServiceIml {

    /**
     * 在Spring中注册时的名称
     */
    public static final String SERVICE_BEAN_NAME = "getCaptchaServiceIml";

    CommonResult<String>  getCaptcha(String email);
}
