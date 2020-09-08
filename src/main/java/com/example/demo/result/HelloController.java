package com.example.demo.result;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 *      https://blog.csdn.net/a1036645146/article/details/108422128
 * @author: xianhao_gan
 * @date: 2020/09/05
 **/
@RestController
@RequestMapping("/hello")
public class HelloController {

    private static final HashMap<String, Object> INFO;

    //因为使用构造方法进行创建对象太麻烦了, 我们使用静态方法来创建对象这样简单明了
    static {
        INFO = new HashMap<>();
        INFO.put("name", "galaxy");
        INFO.put("age", "70");
    }

    @GetMapping("/hello")
    public Map<String, Object> hello() {
        return INFO;
    }

    /**
     * 缺点：想要返回统一的JSON格式需要返回Result<Object>才可以, 我明明返回Object可以了, 为什么要重复劳动, 有没有解决方法
     *
     * @return
     */
    @GetMapping("/result")
    @ResponseBody
    public Result<Map<String, Object>> helloResult() {
        return Result.success(INFO);
    }
}
