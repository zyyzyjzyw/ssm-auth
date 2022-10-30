package com.tedu.java.exception;

import com.tedu.java.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author： zyy
 * @date： 2022/10/30 12:49
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        return Result.fail().message("执行了全局异常!!!!");
    }
    //特定异常处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        return Result.fail().message("执行了特定异常处理!!!!");
    }

    //自定义异常处理
    @ExceptionHandler(OtherException.class)
    @ResponseBody
    public Result error(OtherException e){
        return Result.fail().code(e.getCode()).message(e.getMsg());
    }
}
