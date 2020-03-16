package com.tay.jvm.classloader;

public class MyTest22 {
    static {
        System.out.println("MyTest22 initislizer.");
    }

    public static void main(String[] args) {
        // 因为这两个 class 都在类路径中，所有是系统类加载器加载;
        System.out.println(MyTest22.class.getClassLoader());
        System.out.println(MyTest7.class.getClassLoader());
    }
}
