package com.tay.jvm.classloader;

/**
 * 对于数组，它对应的class对象不是由类加载器加载，而是由JVM在运行期动态的创建。然而对于数组类的类加载器来说，
 * 它返回的类记载其和数组内元素类型的类加载器是一样的（就是相当于数组和数组元素调用.getClassLoader()返回的值是一样的）。
 * 如果数组类元素是基础类，那么数组是没有类加载器的。
 */
public class MyTest15 {
    public static void main(String[] args) {
        // 结果为null，这里的null指的是根类加载器;
        String[] strings = new String[2];
        System.out.println(strings.getClass().getClassLoader());

        // 结果为AppClassLoader，数组元素类型的加载器，这里是应用类加载器;
        MyTest15[] myTest15s = new MyTest15[2];
        System.out.println(myTest15s.getClass().getClassLoader());
        System.out.println(MyTest15.class.getClassLoader());

        // 结果为null，基础类型没有加载器，为null;
        int[] ints = new int[2];
        System.out.println(ints.getClass().getClassLoader());

    }
}
