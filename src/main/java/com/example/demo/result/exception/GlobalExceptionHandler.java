package com.example.demo.result.exception;

import com.example.demo.result.Result;
import com.example.demo.result.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * @description:
 * @author: xianhao_gan
 * @date: 2020/09/05
 **/
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public Result<String> execptionHandler(HttpServletRequest request, Exception e) {
        logger.error("捕获全局异常：" + e);
        e.printStackTrace();
        if (e instanceof GlobalException) {
            GlobalException ex = (GlobalException) e;
            if(StringUtils.isNotBlank(ex.getExtMsg())){
                return Result.error(ex.getResultCode(),ex.getExtMsg());
            }
            return Result.error(ex.getResultCode());
        }else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            if (ex.getBindingResult().hasErrors()) {
                return Result.error(ResultCode.PARAM_ERROR,ex.getBindingResult().getFieldError().getDefaultMessage());
            }
            throw new GlobalException(ResultCode.PARAM_ERROR, ex.getBindingResult().getFieldError().getDefaultMessage());
        }else if (e instanceof ConstraintViolationException) {
            ConstraintViolationException ex = (ConstraintViolationException) e;
            return Result.error(ex.getMessage());
        }
        else {
            return Result.error(ResultCode.ERROR);
        }

    }
}
