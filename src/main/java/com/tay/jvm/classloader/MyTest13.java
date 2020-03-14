package com.tay.jvm.classloader;

/**
 * 输出类加载器的层次结构
 */
public class MyTest13 {
    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();// AppClassLoader;
        System.out.println(classLoader);
        System.out.println("-----------");
        while (null != classLoader) {
            classLoader = classLoader.getParent();
            System.out.println(classLoader);// ExtClassLoader;
            System.out.println("-----------");
            classLoader = classLoader.getParent();
            System.out.println(classLoader);// null（根类加载器）;
        }
    }
}
