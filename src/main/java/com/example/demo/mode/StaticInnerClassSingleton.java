package com.example.demo.mode;

/**
 * @description: 静态内部类实现单例模式
 * @author: stwen_gan
 * @date: 2020/05/14
 **/
public class StaticInnerClassSingleton {

    private static StaticInnerClassSingleton instance;

    //构造私有化
    private StaticInnerClassSingleton(){}

    /**
     * 静态内部类的优点是：
     * 外部类加载时并不需要立即加载内部类，内部类不被加载则不去初始化INSTANCE，故而不占内存。
     * 即当SingleTon第一次被加载时，并不需要去加载 SingletonInstance，
     * 只有当getInstance()方法第一次被调用时，才会去初始化INSTANCE,
     * 第一次调用getInstance()方法会导致虚拟机加载SingletonInstance内部类，
     * 这种方法不仅能确保线程安全，也能保证单例的唯一性，同时也延迟了单例的实例化
     */
    public static class SingletonInstance{
        private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }
    //静态内部类又是如何实现线程安全的呢？
    //https://www.cnblogs.com/niuyourou/p/11892617.html
    public static StaticInnerClassSingleton getInstance(){
        return SingletonInstance.INSTANCE;
    }
}
