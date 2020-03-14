package com.tay.jvm.classloader;

public class MyCat {

    public MyCat() {
        // this.getClass()获取调用类所对应的唯一的class对象，
        // 因为类加载器加载的是类对应的Class对象，因此Class对象中有getClassLoader()方法;
        System.out.println("MyCat is loaded by:" + this.getClass().getClassLoader());
        // 步骤一：在 MyCat 的构造方法中引用 MySample 的一个 Class 对象
        System.out.println("from MyCat: "+MySample.class);
    }
}
