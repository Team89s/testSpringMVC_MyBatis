package com.igeek.ssm.exception;

/**
 * @version 1.0
 * @Description CustomException 自定义异常
 * @Author chenmin
 * @Date 2021/3/10 15:06
 */
public class CustomException extends Exception {

    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }
}
