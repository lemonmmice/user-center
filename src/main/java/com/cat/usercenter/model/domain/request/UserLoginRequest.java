package com.cat.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LCY
 * @date Created in 2022/7/5 19:20
 */
@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = 3191241716373120793L;

    private String userAccount;

    private String userPassword;

}
