package com.example.demo.mode;

/**
 * 单例模式
 * 实现单例模式三个主要特点：1、构造方法私有化；2、实例化的变量引用私有化；3、获取实例的方法共有。
 * https://blog.csdn.net/a1036645146/article/details/106114773
 * 饿汉式，类加载时就实例化了
 **/
public class Singleton {

    private static final Singleton INSTANCE = new Singleton();
    //构造私有化
    private Singleton(){};
    public static Singleton getInstance(){
        return INSTANCE;
    }
}

/**
 * 懒汉式，第一次调用才实例化
 */
class Singleton1 {
    private static Singleton1 INSTANCE = null;
    //构造私有化
    private Singleton1(){};

    // 方式一、存在多线程安全问题
    public static Singleton1 getInstance(){
        if(INSTANCE==null){
            INSTANCE = new Singleton1();
        }
        return INSTANCE;
    }

    // 方式二、同步静态方法，
    //效率低，第一次加载需要实例化，反应稍慢。每次调用getInstance方法都会进行同步，消耗不必要的资源
    public static  synchronized Singleton1 getInstance2(){
        if(INSTANCE==null){
            INSTANCE = new Singleton1();
        }
        return INSTANCE;
    }

    // 方式三、双重检查单例(DCL实现单例)，同步代码块
    //优点：资源利用率高，第一次执行方法是单例对象才会被实例化。
    //缺点：第一次加载时会稍慢，jdk1.5之之前有可能会加载会失败。
    //1、线程间共享变量不可见性。2、无序性(执行顺序无法保证)。
    //---》改进：INSTANCE加Volatile保证可见性
    public static Singleton1 getInstance3(){
        if(INSTANCE==null){
            synchronized (Singleton1.class){
                if(INSTANCE==null){
                    INSTANCE = new Singleton1();
                }
            }
        }
        return INSTANCE;
    }

}