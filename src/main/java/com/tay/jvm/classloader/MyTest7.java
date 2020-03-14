package com.tay.jvm.classloader;

/**
 * java.lang.String是有根类加载器加载，在rt.jar包下
 */
public class MyTest7 {
    public static void main(String[] args) throws Exception {
        // 加载之前需要获取class对象;
        Class<?> clazz = Class.forName("java.lang.String");
        // 返回针对该类的类加载器（就是实际加载该类的加载器），其中null表示启动类（根类）的加载器;
        System.out.println(clazz.getClassLoader());

        Class<?> clazz2 = Class.forName("com.tay.jvm.classloader.c");
        System.out.println(clazz2.getClassLoader()); //打印 sun.misc.Launcher$AppClassLoader@18b4aac2
    }
}

class c {}