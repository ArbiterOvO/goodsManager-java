package com.arbiter.goodsmanager.exception;

import com.arbiter.goodsmanager.result.Result;
import io.jsonwebtoken.JwtException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //JWT令牌出错
    @ExceptionHandler(JwtException.class)
    public Result JwtError(JwtException e) {
        e.printStackTrace();
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)//捕获所有异常
    public Result exception(Exception e) {
        e.printStackTrace();
        return Result.error("操作失败，联系管理员");
    }

}
