package com.tay.jvm.classloader;

import java.util.Random;

/**
 * 当一个接口在初始化时, 并不要求其父接口都完成了初始化;
 * 只有在真正使用到父接口的时候(如引用接口中锁定义的常量时), 才会初始化;
 * <p>
 * 如果在一个接口中声明一个常量（b=5），而且该常量在编译期就能完全确定好具体的数值，那么就不会加载这个接口，
 * 而是直接把这个常量值直接纳入了MyTest5的常量池中。
 * 译运行发现根本没有加载 MyParent5和 MyChild5，仅仅加载了 MyTest5，同时将两者的 class 文件删除之后仍然可以运行，
 * 若编译期无法确定具体的数值，如b=new Random().nextInt()，那么删除MyParent5和 MyChild5的class文件就会报错。
 */
public class MyTest5 {
    public static void main(String[] args) {
        System.out.println(MyChild5.b);
    }
}

interface MyParent5 {
    public static final int a = new Random().nextInt();
}

interface MyChild5 extends MyParent5 {
    public static final int b = new Random().nextInt();
}

class MyParent5_1 {
    public static final int a = 6;
}

/**
 * 改为class在之后，就不是 final了，就不会纳入MyTest5的常量池中，所以程序运行期间肯定要加载MyChild5
 */
class MyChild5_1 extends MyParent5_1 {
    public static int b = 5;
}