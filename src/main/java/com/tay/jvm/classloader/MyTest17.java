package com.tay.jvm.classloader;

/**
 * 创建自定义加载器，继承ClassLoader
 */
public class MyTest17 {
    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");
        // 返回的 class 对象就是 MySample 类对应的 Class 对象，下面可以通过反射创建 MySample 的一个实例
        Class<?> clazz = loader1.loadClass("com.tay.jvm.classloader.MySample");
        System.out.println("class:" + clazz.hashCode());
        // 如果注释掉该行，就不会实例化MySample对象，那么MySample的构造方法不会调用，
        // 因此不会实例化MyCat对象，即没有对MyCat主动使用（初始化），这里也就没有加载MyCat的class;
        Object object = clazz.newInstance();
    }
}
