package com.example.demo.result.exception;

import com.example.demo.result.ResultCode;
import lombok.Getter;

/**
 * @description:
 * @author: xianhao_gan
 * @date: 2020/09/05
 **/
@Getter
public class GlobalException extends RuntimeException {
    private static long serialVersionUID = 1L;

    private ResultCode resultCode;
    /**
     * 额外的错误信息
     */
    private String extMsg;

    public GlobalException(ResultCode resultCode) {
        super(resultCode.toString());
        this.resultCode = resultCode;
        this.extMsg = null;
    }

    public GlobalException(ResultCode resultCode, String extMsg) {
        super(resultCode.toString());
        this.resultCode = resultCode;
        this.extMsg = extMsg;
    }

}
