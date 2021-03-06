package com.tay.jvm.classloader;

public class MyTest12 {
    public static void main(String[] args) throws Exception {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        // 调用ClassLoader的loadClass方法加载一个类，并不是对类的主动使用，不会导致类的初始化;
        Class<?> clazz = loader.loadClass("com.tay.jvm.classloader.CL");
        System.out.println(clazz);
        System.out.println("=============");
        // 反射（7中主动加载之一）会导致类的初始化;
        clazz = clazz.forName("com.tay.jvm.classloader.CL");
        System.out.println(clazz);
    }
}

class CL {
    static {
        System.out.println("Class CL");
    }
}