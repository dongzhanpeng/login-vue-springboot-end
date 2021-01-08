package com.dzp.login.loginsystem.service;

import com.dzp.login.loginsystem.api.CommonResult;
import com.dzp.login.loginsystem.dao.RegisterDao;
import com.dzp.login.loginsystem.entity.RegisterUserEntiy;
import com.dzp.login.loginsystem.entity.UserEntity;
import com.dzp.login.loginsystem.serviceIml.RegisterServiceIml;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;


/**
 * @Author: dongzhanpeng
 * @Date: 2021-1-4 16:27
 * @Version 1.0
 */
@Transactional
@Service
public class RegisterService implements RegisterServiceIml {

    @Qualifier("registerDao")
    @Autowired
    private RegisterDao registerDao;

    @Qualifier("userEntity")
    @Autowired
    private UserEntity userEntity;

    @Override
    public CommonResult doRegister(RegisterUserEntiy registerUserEntiy) {

        //获取用户名
        String username = registerUserEntiy.getUsername();
        //查询是否存在相同的用户
        UserEntity userEntity = registerDao.findByUsername(username);

        if (!ObjectUtils.isEmpty(userEntity)) {
            String registerUsername = userEntity.getUsername();
            if (username.equals(registerUsername)) {
                return CommonResult.failed("注册失败，用户名已存在");
            }
        }

        //区分是邮箱注册还是手机号注册
        CommonResult result = IsPhoneOrEmail(registerUserEntiy);
        return result;
    }

    //区分是邮箱注册还是手机号注册
    public CommonResult IsPhoneOrEmail(RegisterUserEntiy registerUserEntiy) {
        String emailOrPhoneNumber = registerUserEntiy.getEmailoriphonenumber();
        if (emailOrPhoneNumber.contains("@") && emailOrPhoneNumber.contains(".com")) {
            String email = emailOrPhoneNumber;
            CommonResult result = verifyEmailIsNull(registerUserEntiy, email);
            return result;
        } else if (emailOrPhoneNumber.length() == 11) {
            String phoneNumber = emailOrPhoneNumber;
                CommonResult result = verifyPhoneNumberIsNull(registerUserEntiy,phoneNumber);
            return result;
        }else {
            return CommonResult.failed("请输入合法的手机号或邮箱号");
        }
    }

    //验证手机号是否被注册
    public CommonResult verifyPhoneNumberIsNull(RegisterUserEntiy registerUserEntiy,String phoneNumber) {
        UserEntity userEntitys = registerDao.findByPhonenumber(phoneNumber);
        if (!ObjectUtils.isEmpty(userEntitys)) {
            if (phoneNumber.equals(userEntitys.getPhonenumber())){
                return CommonResult.failed("手机号已被注册，请重新输入");
            }
        }else {
            String username = registerUserEntiy.getUsername();
            String password = registerUserEntiy.getPassword();

            //密码使用md5加密
            password = DigestUtils.md5DigestAsHex(password.getBytes());
            userEntity.setUsername(username);
            userEntity.setPassword(password);
            userEntity.setPhonenumber(phoneNumber);
            registerDao.save(userEntity);
        }
        return CommonResult.success("您已使用手机号注册成功！！");
    }

    //验证邮箱是否被注册
    public CommonResult verifyEmailIsNull(RegisterUserEntiy registerUserEntiy,String email) {
        UserEntity userEntitys = registerDao.findByEmail(email);
        if (!ObjectUtils.isEmpty(userEntitys)) {
            if (email.equals(userEntitys.getEmail())){
                return CommonResult.failed("邮箱已被注册，请重新输入");
            }
        }else {
            String username = registerUserEntiy.getUsername();
            String password = registerUserEntiy.getPassword();

            //密码使用md5加密
            password = DigestUtils.md5DigestAsHex(password.getBytes());
            userEntity.setUsername(username);
            userEntity.setPassword(password);
            userEntity.setEmail(email);
            registerDao.save(userEntity);
        }
        return CommonResult.success("您已使用邮箱注册成功！！");
    }
}
