package com.tay.jvm.classloader;

public class MyTest25 implements Runnable {
    private Thread thread;

    public MyTest25() {
        // 使用new就会执行构造方法，救护创建该线程对象;
        this.thread = new Thread(this);
        // 通过start就会执行下面的run方法;
        this.thread.start();
    }

    @Override
    public void run() {
        // 获取线程的上下文类加载器;
        // 如果没有调用getContextClassLoader，则默认为父线程的contextClassLoader。
        // 原始线程的contextClassLoader通常设置为用于加载应用程序的类加载器，也即AppClassLoader。
        // 为什么默认的线程上下文类加载器是应用类加载器，原因在 sun.misc.Launcher代码中;
        ClassLoader classLoader = this.thread.getContextClassLoader();
        System.out.println("classLoader:" + classLoader.getClass());// 打印AppClassLoader；
        // 设置该线程的上下文ClassLoader;
        this.thread.setContextClassLoader(classLoader);
        System.out.println("Class:" + classLoader.getClass());
        System.out.println("Parent:" + classLoader.getParent().getClass());
    }

    public static void main(String[] args) {
        new MyTest25();
    }
}
