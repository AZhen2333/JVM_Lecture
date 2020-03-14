package com.tay.jvm.classloader;

public class MyTest11 {
    public static void main(String[] args) {
        // 因为a是定义在父类中，因此是对于父类的主动使用（静态变量定义在那（即谁拥有）就是对谁的主动引用）;
        System.out.println(myChild11.a);
        myChild11.doSomething();
    }
}

class myParent11 {
    static int a = 3;

    static void doSomething() {
        System.out.println("doSomething");
    }
}

class myChild11 extends myParent11 {
    static int b = 4;

    static {
        System.out.println("myChild10 static block");
    }
}
