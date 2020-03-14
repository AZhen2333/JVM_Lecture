package com.tay.jvm.classloader;


import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class MyTest14 {
    public static void main(String[] args) throws IOException {
        // 获取当前线程的上下文加载器;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println(loader);

        String soureceName = "com/tay/jvm/classloader/MyTest13.class";
        Enumeration<URL> urls = loader.getResources(soureceName);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            System.out.println(url);
        }

    }
}
