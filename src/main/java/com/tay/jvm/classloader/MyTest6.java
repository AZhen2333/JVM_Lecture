package com.tay.jvm.classloader;

/**
 * 准备阶段和初始化的顺序问题
 */
public class MyTest6 {
    public static void main(String[] args) {
        // 首先调用类 Singleton 的静态方法，表示对该类的主动使用;
        Singleton sinstance = Singleton.getInstance();
        System.out.println("counter1:" + Singleton.counter1);
        System.out.println("counter1:" + Singleton.counter2);
    }
}

class Singleton {
    public static int counter1 = 1;

    private static Singleton singleton = new Singleton();

    private Singleton() {
        counter1++;
        counter2++; // 准备阶段的重要意义;
    }

    public static int counter2 = 0;

    public static Singleton getInstance() {
        return singleton;
    }
}
