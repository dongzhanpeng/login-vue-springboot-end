package com.dzp.login.loginsystem.controller;

import com.dzp.login.loginsystem.api.CommonResult;
import com.dzp.login.loginsystem.serviceIml.ForgetPasswordServiceIml;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <>获取验证码接口</>
 *
 * @Author: dongzhanpeng
 * @Date: 2020-12-fdasfa28 15:50c
 * @Version 1.0
 */
@RestController
public class ForgetPasswordController {

    @Autowired
    private ForgetPasswordServiceIml forgetPasswordServiceIml;

    /**
     * 获取验证码
     *
     * @param emailBody
     * @return
     */
    @RequestMapping(value = "/admin/getCaptcha", method = RequestMethod.POST)
    public CommonResult<String> getCaptcha(@RequestBody Map<Object, String> emailBody) {
        if (emailBody.isEmpty()) {
            throw new RuntimeException("email为空");
        }
        String email = emailBody.get("email");
        if (StringUtils.isEmpty(email)) {
            throw new RuntimeException("email为空");
        }
        CommonResult<String> commonResult = forgetPasswordServiceIml.getCaptcha(email);
        return commonResult;
    }

    /**
     * 验证码确认
     *
     * @param enSureBody
     * @return
     */
    @RequestMapping(value = "admin/enSure", method = RequestMethod.POST)
    public CommonResult<String> enSure(@RequestBody Map<Object, String> enSureBody) {

        if (enSureBody.isEmpty()) {
            throw new RuntimeException("验证码为空");
        }

        String captcha = enSureBody.get("captcha");
        String phoneNumber = enSureBody.get("phoneNumber");
        if (StringUtils.isEmpty(captcha)) {
            throw new RuntimeException("验证码为空");
        }
        if (phoneNumber.isEmpty()) {
            throw new RuntimeException("手机号为空");
        }
        CommonResult<String> commonResult = forgetPasswordServiceIml.enSure(captcha, phoneNumber);
        return commonResult;
    }

}


