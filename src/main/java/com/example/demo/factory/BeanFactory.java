package com.example.demo.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @description: 静态工厂类，读取配置文件获取类路径，通过反射创建实例
 * @author: xianhao_gan
 * @date: 2020/12/31
 **/
public class BeanFactory {
    /**
     * 通过properties类获取配置文件中的配置
     */
    private static Properties properties = new Properties();

    static {
        try {
            //1. 获得IO输入流
            InputStream inputStream = BeanFactory.class.getClassLoader().getResourceAsStream("applicationContext.properties");
            //2. 加载流
            properties.load(inputStream);
            //3. 关闭流
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Car getCarByName(String beanName){
        Car car = null;
        try {
            Class clazz = Class.forName(properties.getProperty(beanName));
            //创建对象
            car = (Car) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return car;
    }
}
