package com.dzp.login.loginsystem.service;

import com.dzp.login.loginsystem.api.CommonResult;
import com.dzp.login.loginsystem.dao.ForgetPasswordDao;
import com.dzp.login.loginsystem.entity.UserEntity;
import com.dzp.login.loginsystem.serviceIml.ForgetPasswordServiceIml;
import com.dzp.login.loginsystem.util.CreateCaptchaUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

/**
 * @Author: dongzhanpeng
 * @Date: 2020-12-29 12:06
 * @Version 1.0
 */
@Transactional
@Service
public class ForgetPasswordService implements ForgetPasswordServiceIml {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.mail.fromMail.sender}")
    private String sender;

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private CreateCaptchaUtil createCaptchaUtil;

    @Qualifier("forgetPasswordDao")
    @Autowired
    private ForgetPasswordDao forgetPasswordDao;

    /**
     * 获取验证码Service
     *
     * @param email
     * @return
     */
    @Override
    public CommonResult<String> getCaptcha(String email) {


        String captcha = createCaptchaUtil.createCaptcha();
        captcha = DigestUtils.md5DigestAsHex(captcha.getBytes());

        forgetPasswordDao.updateCaptchaByEmail(captcha, email);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(email);
        message.setSubject("董占鹏的邮件");
        message.setText(captcha);
        try {
            mailSender.send(message);
            logger.info("验证码已发送。");
        } catch (Exception e) {
            logger.error("验证码发送异常！", e);
        }
        return CommonResult.success("验证码发送成功");
    }

    /**
     * 验证码确认Service
     *
     * @param captcha
     * @param phoneNumber
     * @return
     */
    @Override
    public CommonResult<String> enSure(String captcha, String phoneNumber) {
        UserEntity userEntitySave = forgetPasswordDao.findByPhonenumber(phoneNumber);
        if (ObjectUtils.isEmpty(userEntitySave)) {
            return CommonResult.failed("用户信息为空");
        } else {
            if (userEntitySave.getCaptcha().isEmpty()) {
                return CommonResult.failed("用户验证码为空");
            } else {
                String captchaSave = userEntitySave.getCaptcha();
                if (captchaSave.equals(captcha)) {
                    return CommonResult.success("验证码正确，请重置密码");
                } else {
                    return CommonResult.failed("验证码错误，请重新输入");
                }
            }
        }
    }
}
