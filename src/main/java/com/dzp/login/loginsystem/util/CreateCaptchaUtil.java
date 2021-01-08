package com.dzp.login.loginsystem.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;


/**
 * <>生成随机验证码</>
 * @Author: dongzhanpeng
 * @Date: 2020-12-29 13:21
 * @Version 1.0
 */
@Component
public class CreateCaptchaUtil {

    public static final String UTIL_BEAN_NAME = "createCaptchaUtil";

    /**
     * 产生随机验证码
     */


    public String createCaptcha()
    {
        String uuid = UUID.randomUUID().toString();
        String captcha = uuid.substring(0, 6);
        return captcha;
    }

}
