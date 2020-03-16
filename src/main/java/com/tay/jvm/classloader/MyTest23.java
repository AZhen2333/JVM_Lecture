package com.tay.jvm.classloader;

import sun.misc.Launcher;

public class MyTest23 {

    public static void main(String[] args) {
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));

        // 结果为 null，表示启动类加载器
        System.out.println(ClassLoader.class.getClassLoader());
        // 结果为 null，表示启动类加载器，
        // 那么该加载器同样会尝试加载 Launcher 类依赖的所有组件，
        // 因为这两个加载器前面没有 public 修饰，
        // 所以只能看 Launcher 类是什么加载器进行加载的;
        System.out.println(Launcher.class.getClassLoader());

        System.out.println(System.getProperty("java.system.class.loader"));

        System.out.println(ClassLoader.getSystemClassLoader());
    }

}
