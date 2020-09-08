package com.example.demo.result;


import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ResponseBody // 创建一个注解类继承@ResponseBody
public @interface ResponseResultBody {

}
