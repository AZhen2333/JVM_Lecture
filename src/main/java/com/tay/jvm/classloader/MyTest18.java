package com.tay.jvm.classloader;

public class MyTest18 {
    public static void main(String[] args) {
        //根加载器路径;
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println("===================");
        //扩展类加载器路径;
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println("===================");
        //应用类加载器路径;
        System.out.println(System.getProperty("java.class.path"));
    }
}
