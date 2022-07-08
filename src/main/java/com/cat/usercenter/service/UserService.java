package com.cat.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cat.usercenter.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LCY
 * @date Created in 2022/7/2 18:23
 * 用户服务
 */

public interface UserService extends IService<User> {

    /**
     * 用户注释
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 用户id
     */
   public long userRegister(String userAccount,String userPassword,String checkPassword,String planetCode);

    /**
     *用户 登录
     * @param userAccount
     * @param userPassword
     * @return 脱敏后的用户信息
     */
    User UserLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     * @param originUser
     * @return
     */
    User getSafetyUser(User originUser);

    /**
     * 用户注销
     */

    int userLogout(HttpServletRequest request);
}
