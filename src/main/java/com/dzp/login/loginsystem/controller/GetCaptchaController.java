package com.dzp.login.loginsystem.controller;

import com.dzp.login.loginsystem.api.CommonResult;
import com.dzp.login.loginsystem.serviceIml.GetCaptchaServiceIml;
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
 * @Date: 2020-12-28 15:50
 * @Version 1.0
 */
@RestController
public class GetCaptchaController {

    @Autowired
    private GetCaptchaServiceIml getCaptchaServiceIml;

    @RequestMapping(value = "/admin/getCaptcha", method = RequestMethod.POST)
    public CommonResult<String> getCaptcha(@RequestBody Map<Object, String> emailBody) {
        if (emailBody.isEmpty()) {
            throw new RuntimeException("email为空");
        }
        String email = emailBody.get("email");
        if (StringUtils.isEmpty(email)) {
            throw new RuntimeException("email为空");
        }
        CommonResult<String> commonResult = getCaptchaServiceIml.getCaptcha(email);
        return commonResult;
    }
}


