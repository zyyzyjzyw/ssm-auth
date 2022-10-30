package com.tedu.java.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author： zyy
 * @date： 2022/10/30 12:54
 * @description： TODO
 * @version: 1.0
 * @描述：自定义异常
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtherException extends RuntimeException{
    private Integer code;
    private String msg;
}
