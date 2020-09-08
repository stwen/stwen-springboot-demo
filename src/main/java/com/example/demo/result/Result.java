package com.example.demo.result;

import lombok.Getter;
import lombok.ToString;

/**
 * @description: 统一响应结果
 * @author: xianhao_gan
 * @date: 2020/09/05
 **/
@Getter
@ToString
public class Result<T> {

    private Integer code;

    private String msg;

    private T data;

    //无参构造，json反序列化需要
    private Result() {

    }

    /**
     * 避免用户直接调用构造方法
     */
    private Result(T data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
        this.data = data;
    }

    /**
     * 避免用户直接调用构造方法
     */
    private Result(T data,Boolean flag) {
        if(!flag){
            this.code = ResultCode.ERROR.getCode();
            this.msg = ResultCode.ERROR.getMsg();
            this.data = data;
        }else{
            new Result<T>(data);
        }

    }

    private Result(ResultCode resultCode) {
        if (resultCode == null) {
            return;
        }
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    /**
     * extMsg:额外的错误信息，和msg拼接在一起
     */
    private Result(ResultCode resultCode, String extMsg) {
        if (resultCode == null) {
            return;
        }
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg() + ":" + extMsg;
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }


    /**
     * 0-操作成功
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> error(T data) {
        return new Result<T>(data,false);
    }

    public static <T> Result<T> error(ResultCode resultCode) {
        return new Result<T>(resultCode);
    }


    public static <T> Result<T> error(ResultCode resultCode, String extMsg) {
        return new Result<T>(resultCode, extMsg);
    }

}
