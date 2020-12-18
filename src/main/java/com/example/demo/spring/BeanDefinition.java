package com.example.demo.spring;

/**
 * @description: bean定义，保存spring bean（有Component注解的类）
 * @author: xianhao_gan
 * @date: 2020/10/28
 **/
public class BeanDefinition {

    // 类信息
    private Class beanClass;

    // bean类型范围：单例、原型
    private String scope;

    // 是否懒加载
    private Boolean isLazy;

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Boolean getLazy() {
        return isLazy;
    }

    public void setLazy(Boolean lazy) {
        isLazy = lazy;
    }
}
