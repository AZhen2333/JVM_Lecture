package com.tay.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 创建自定义加载器，需要继承 ClassLoader
 */
public class MyTest16 extends ClassLoader {
    private String classLoaderName;

    // 从哪进行加载，如果没有指定就从项目下;
    private String path;

    // 指定类的后缀名;
    private final String fileExtension = ".class";

    public MyTest16(String classLoaderName) {
        super();// 默认将系统类加载器当做该类加载器的父加载器;
        this.classLoaderName = classLoaderName;
    }

    public MyTest16(ClassLoader parent, String classLoaderName) {
        super(parent);// 显式指定该类加载器的父加载器;
        this.classLoaderName = classLoaderName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "MyTest16{" +
                "classLoaderName='" + classLoaderName + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                '}';
    }

    // 根据className来寻找该类，该类再检查完父类加载器后自动被loadClass调用，
    // 而这里我们没有重写loadClass方法，因此会自动调用;
    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        byte[] data = this.loaderClassData(className);
        System.out.println("findClass invoked:" + className);
        System.out.println("class loader name:" + this.classLoaderName);
        return this.defineClass(classLoaderName, data, 0, data.length);
    }

    // 根据类的名字，将类的二进制数组数组加载出来(将它的文件找到，然后以输入输出流的方式返回字节数组，
    // 该字节数组就是从文件中读取出的class文件的二进制信息)
    private byte[] loaderClassData(String className) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;

        try {
            this.classLoaderName = this.classLoaderName.replace(".", "/");
            is = new FileInputStream(new File(className + this.fileExtension));
            baos = new ByteArrayOutputStream();

            int ch = 0;
            while (-1 != (ch = is.read())) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");
//        test(loader1);
        // 路径设置为classpath的路径，此时加载MyTest1，是由MyTest16委托父亲的类加载器进行加载（System）;
        loader1.setPath("/Users/tay/Tay_All/workspace/IDEA_workspace/jvm_lecture/target/classes");

        // 将class文件移至自定义路径，设置加载路径为自定义的路径，删除/target/classes下的class文件，
        // 此时加载MyTest1，因为MyTest16的父亲的类加载器无法在target/classes下找到class文件，因此会向下委派自定义的类加载器（MyTest16）进行到指定的路径进行加载。
        // 如果不删除/target/classes下的class文件，同样会由System进行加载;
        loader1.setPath("/Users/tay/Desktop/");

        Class<?> clazz = loader1.loadClass("com.tay.jvm.classloader.MyTest1");
        System.out.println(clazz.hashCode());
        Object object = clazz.newInstance();
        System.out.println(object.getClass().getClassLoader());


        // 创建loader2类加载器，指定和loader1相同的加载类路径
        // 1.如果classpath下的MyTest1.class文件未被删除，那么loader1和loader2委托父亲的类加载器加载时，都能找到class文件，
        // 此时loader1委托system加载了clazz，loader2再次委托system加载时，不会再次加载，会引用loader1已加载的，因此两处打印信息会相同。
        // 2.如果已删除classpath下的MyTest1.class文件，那么loader1和loader2在创建时，因为是创建了新对象，因此会产生各自的命名空间，而不同命名空间是可以多次加载相同类的，
        // 因此打印信息不一致。
        MyTest16 loader2 = new MyTest16("loader2");
        loader2.setPath("/Users/tay/Desktop/");
        Class<?> clazz2 = loader2.loadClass("com.tay.jvm.classloader.MyTest1");
        System.out.println(clazz2.hashCode());
        Object object2 = clazz.newInstance();
        System.out.println(object2.getClass().getClassLoader());
    }

    public static void test(ClassLoader classLoader) throws Exception {
        Class<?> clazz = classLoader.loadClass("com.tay.jvm.classloader.MyTest1");
        Object object = clazz.newInstance();
        System.out.println(object);
        System.out.println(object.getClass().getClassLoader());
    }
}
