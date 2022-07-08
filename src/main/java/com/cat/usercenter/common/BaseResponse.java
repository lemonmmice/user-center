package com.cat.usercenter.common;

import lombok.Data;

import java.io.Serializable;

/**通用返回类
 * @author LCY
 * @date Created in 2022/7/7 11:00
 */

@Data
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = -4624129467241975416L;

    private int code;

    private T data;

    private String message;

    public BaseResponse(int code, T data, String message,String description) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data,String message) {
       this(code,data, message,"");
    }
    public BaseResponse(int code, T data) {
        this(code,data, "","");
    }
    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(),null,errorCode.getMessage(),errorCode.getDescription());
    }

}
