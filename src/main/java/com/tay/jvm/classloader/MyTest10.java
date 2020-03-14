package com.tay.jvm.classloader;

public class MyTest10 {
    static {
        System.out.println("MyTest10 static block");
    }

    public static void main(String[] args) {
        System.out.println(myChild10.a);
    }
}

class myParent10{
    static int a = 3;
    static {
        System.out.println("myParent10 static block");
    }
}

class myChild10 extends myParent10{
    static int b = 4;
    static {
        System.out.println("myChild10 static block");
    }
}
