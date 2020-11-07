package com.example.demo.spring;

import com.example.demo.spring.annotation.Autowired;
import com.example.demo.spring.annotation.Component;
import com.example.demo.spring.annotation.ComponentScan;
import com.example.demo.spring.annotation.Scope;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: spring上下文
 * @author: xianhao_gan
 * @date: 2020/10/28
 **/
public class MySpringApplicationContext {

    // 属性：配置类
    private Class configClass;

    // 保存 beanDefinition
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    // 单例池
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    /**
     * 构造注入 配置类
     *
     * @param configClass
     */
    public MySpringApplicationContext(Class configClass) {
        this.configClass = configClass;

        // 1、扫描，得到class,如：com.example.demo.spring.service.UserService
        List<Class> classList = scan(configClass);

        // 2、解析这些类 classList --> BeanDefinition --> 保存到 beanDefinitionMap
        for (Class clazz : classList) {
            if (clazz.isAnnotationPresent(Component.class)) {//含有Component注解的，表示该类是一个bean
                BeanDefinition beanDefinition = new BeanDefinition();
                beanDefinition.setBeanClass(clazz);

                Component componentAnnotation = (Component) clazz.getAnnotation(Component.class);

                String beanName = componentAnnotation.value();
                if (clazz.isAnnotationPresent(Scope.class)) {
                    Scope scopeAnnotation = (Scope) clazz.getAnnotation(Scope.class);
                    beanDefinition.setScope(scopeAnnotation.value());
                } else {
                    //默认单例
                    beanDefinition.setScope("singleton");
                }
                beanDefinitionMap.put(beanName, beanDefinition);
            }
        }

        // 3、基于class 创建实例化bean
        instanceSingletonBean();
    }

    /**
     * 基于class 创建实例化单例bean
     */
    private void instanceSingletonBean() {

        for (String beanName : beanDefinitionMap.keySet()) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (beanDefinition.getScope().equals("singleton")) {
                //创建 bean
                if (!singletonObjects.containsKey(beanName)) {
                    Object bean = doCreateBean(beanName, beanDefinition);
                    singletonObjects.put(beanName, bean);//放入单例池 map
                }
            }
        }
    }

    /**
     * 创建bean
     *
     * @param beanName
     * @param beanDefinition
     * @return
     */
    private Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        try {
            // 1、实例化
            Class beanClass = beanDefinition.getBeanClass();
            Object bean = beanClass.getDeclaredConstructor().newInstance();// 反射

            //2.属性填充
            Field[] declaredFields = beanClass.getDeclaredFields();
            for (Field field : declaredFields) {
                // 依赖注入，含有Autowired注解的
                if (field.isAnnotationPresent(Autowired.class)) {
                    //属性赋值
                    Object o = getBean(field.getName());
                    field.setAccessible(true);
                    field.set(bean,o);
                }
            }

            //3. Aware 设置bean名称
            if(bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(beanName);
            }

            // 初始化之前 xxxx
            // 如果Bean实现了BeanPostProcessor接口，Spring就将调用他们的postProcessBeforeInitialization()方法

            //4.初始化
            // 如果Bean 实现了InitializingBean接口，Spring将调用他们的afterPropertiesSet()方法。
            // 类似的，如果bean使用init-method声明了初始化方法，该方法也会被调用
            if(bean instanceof InitializingBean){
                ((InitializingBean) bean).afterPropertiesSet();
            }

            // 初始化之后 xxxx
            // 如果Bean 实现了BeanPostProcessor接口，Spring就将调用他们的postProcessAfterInitialization()方法。

            return bean;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 通过bean名 获取bean对象
     *
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) {

        //判断bean是 单例、原型
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition.getScope().equals("prototype")) {
            // 原型模式：每次都创建新的bean
            Object bean = doCreateBean(beanName, beanDefinition);
            return bean;
        } else if (beanDefinition.getScope().equals("singleton")) {
            // 单例：单例池里面去拿
            Object bean = singletonObjects.get(beanName);

            // todo 假如通过 Autowired注入的bean属性，还没有创建，就会是null
            if (bean == null) {
                Object bean1 = doCreateBean(beanName, beanDefinition);
                singletonObjects.put(beanName, bean1);//放入单例池
                return bean1;
            }
            return bean;
        }

        return null;
    }

    /**
     * 扫描，获取包路径下的类
     *
     * @param configClass
     * @return
     */
    private List<Class> scan(Class configClass) {
        List<Class> classList = new ArrayList<>();

        // todo 先判断是否存在扫描类注解 ComponentScan，再获取注解 ComponentScan
        ComponentScan componentScanAnnotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
        // 获取扫描路径
        String path = componentScanAnnotation.value();
        System.out.println(path);

        path = path.replace(".", "/"); // com/stwen/demo/spring/service

        ClassLoader classLoader = MySpringApplicationContext.class.getClassLoader();
        URL url = classLoader.getResource(path);
        File file = new File(url.getFile()); // com/stwen/demo/spring/service文件夹
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                //System.out.println(f);
                //System.out.println(f.getAbsolutePath());// 绝对路径

                // 截取，转换成类的全限定名：包.类,如：com.example.demo.spring.service.User
                String absolutePath = f.getAbsolutePath();// E:\my-demo\stwen-springboot-demo\target\classes\com\example\demo\spring\service\User.class
                String relativePath = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class"));// com\example\demo\spring\service\User
                relativePath = relativePath.replace("\\", ".");
                System.out.println(relativePath); // com.example.demo.spring.service.User

                // 通过类的全限定名，加载类
                try {
                    Class<?> clazz = classLoader.loadClass(relativePath);
                    classList.add(clazz);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(classList);
        return classList;
    }
}
