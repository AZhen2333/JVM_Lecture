package com.tay.jvm.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyTest21 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyTest16 loader1 = new MyTest16("loader1");
        MyTest16 loader2 = new MyTest16("loader2");
        loader1.setPath("C:/Users/Public/Desktop/");
        loader2.setPath("C:/Users/Public/Desktop/");

        // clazz1 和 clazz2 分别由loader1和loader2加载，没有直接或者间接的父子关系，属于不同的而命名空间，不能相互引用;
        Class<?> clazz1 = loader1.loadClass("com.tay.jvm.classloader.MyPerson");
        Class<?> clazz2 = loader1.loadClass("com.tay.jvm.classloader.MyPerson");

        System.out.println(clazz1 == clazz2);

        Object object1 = clazz1.newInstance();
        Object object2 = clazz2.newInstance();

        Method method = clazz1.getMethod("setMyPerson", Object.class);
        method.invoke(object1, object2);
    }
}
