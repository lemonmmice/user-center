package com.cat.usercenter.exception;

import com.cat.usercenter.common.ErrorCode;
import lombok.Data;

/**
 * @author LCY
 * @date Created in 2022/7/7 11:47
 * 自定义异常类
 */
@Data
public class BusinessException extends RuntimeException{

    private final String description;

    private final int code;

    public BusinessException(String message, String description, int code) {
        super(message);
        this.description = description;
        this.code = code;
    }
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }
    public BusinessException(ErrorCode errorCode,String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description ;
    }

}
