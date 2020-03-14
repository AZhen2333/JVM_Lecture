package com.tay.jvm.classloader;

import java.io.*;

/**
 * 关于命名空间的重要说明
 * 1.子加载器所加载的类能够访问到父加载器所加载的类
 * 2.父加载器所加载的类无法访问到子加载器所记载的类
 */
public class MyTest17_1 extends ClassLoader {
    private String classLoaderName;
    private String path;
    private final String fileExtension = ".class";

    public MyTest17_1(String classLoaderName) {
        super();// 将系统类加载器做该类的父加载器;
        this.classLoaderName = classLoaderName;
    }

    public MyTest17_1(ClassLoader parent, String classLoaderName) {
        super(parent);// 显式指定该类的父加载器;
        this.classLoaderName = classLoaderName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("className=" + name);
        name = name.replace(".", File.separator);
        byte[] data = new byte[0];
        try {
            data = loadClassData(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name, data, 0, data.length);
    }

    private byte[] loadClassData(String name) throws IOException {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;
        try {
            is = new FileInputStream(new File(this.path + name + this.fileExtension));
            baos = new ByteArrayOutputStream();
            int ch;
            while (-1 != (ch = is.read())) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            is.close();
            baos.close();
            return data;
        }
    }

    public static void main(String[] args) throws Exception {
        // 1;
//        MyTest17_1 loader1 = new MyTest17_1("loader1");
//        loader1.path = "C:\\Users\\Administrator\\Desktop";
//        Class<?> clazz = loader1.loadClass("com.tay.jvm.classloader.MySample");
//        System.out.println(clazz.hashCode());
//        //MyCat是由加载MySample的加载器去加载的：
//        // 如果只删除classpath下的MyCat，则会报错，NoClassDefFoundError；
//        // 如果只删除calsspath下的MySample，则由自定义加载器加载桌面上的MySample，由系统应用加载器加载MyCat。
//        Object object = clazz.newInstance();


        // 2;
        //修改MyCat后，仍然删除classpath下的MySample，留下MyCat，程序报错
        //因为命名空间，父加载器找不到子加载器所加载的类，因此MyCat找不到
        //MySample。
        MyTest17_1 loader1=new MyTest17_1("loader1");
        loader1.path="C:/Users/Administrator/Desktop/";
        Class<?> clazz=loader1.loadClass("com.tay.jvm.classloader.MySample");
        System.out.println(clazz.hashCode());
        Object  object=clazz.newInstance();
    }
}
