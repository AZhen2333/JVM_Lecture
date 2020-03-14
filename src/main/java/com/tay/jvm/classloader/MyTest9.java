package com.tay.jvm.classloader;

public class MyTest9 {
    static {
        System.out.println("MyTest9 static block");
    }

    public static void main(String[] args) {
        // 打印Child.a，由于并没有主动使用Child类，所以并不会初始化，就不会输出静态代码块的输出语句。
        System.out.println(Child.a);
    }
}

class Parent {
    static int a =3;
    static {
        System.out.println("Parent static block" );
    }
}

class Child extends Parent{
    static int b = 4;
    static {
        System.out.println("Child static block");
    }
}

