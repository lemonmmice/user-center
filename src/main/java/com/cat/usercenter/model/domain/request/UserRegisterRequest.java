package com.cat.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LCY
 * @date Created in 2022/7/5 11:54
 *
 * 用户注册请求

 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 1174368747197081888L;
    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 用户密码
     */
    private String userPassword;
    /**
     * 用户校验密码
     */
    private String checkPassword;
    /**
     * 星球编号
     */
    private String planetCode;
}
