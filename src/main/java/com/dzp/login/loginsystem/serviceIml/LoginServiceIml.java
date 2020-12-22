package com.dzp.login.loginsystem.serviceIml;

import com.dzp.login.loginsystem.api.CommonResult;

public interface LoginServiceIml {

    /**
     * 在Spring中使用时创建的Bean名称.
     */
    public static final String SERVICE_BEAN_NAME = "loginServiceIml";

    /**
     * 查询用户名和密码
     * @param userName
     * @param password
     * @return
     */
    CommonResult<String> findUserNameAndPassword(String userName, String password);
}
