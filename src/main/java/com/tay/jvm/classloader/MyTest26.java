package com.tay.jvm.classloader;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 线程上下文类加载器的一般使用模式
 */
public class MyTest26 {
    public static void main(String[] args) {
        // 一旦加入下面此行，将使用ExtClassLoader去加载Driver.class，
        // ExtClassLoader不会去加载classpath，因此无法找到MySql的相关驱动。
        // Thread.getCurrentThread().setContextClassLoader(MyTest26.class.getClassLoader().parent());

        // ServiceLoader服务提供者，加载实现的服务。
        // 服务就是一系列接口和类（通常为抽象类）的集合，服务提供者就是服务的一个特定的实现，所以 ServiceLoader 是用于加载服务的具体实现；
        // 服务提供者继承或者实现服务的抽象类或者接口。
        // 服务提供者放在 META-INF/services/服务的完全限定名字。
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();
        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            System.out.println("driver:" + driver.getClass() + ",loader" + driver.getClass().getClassLoader());
        }
        System.out.println("当前上下文加载器" + Thread.currentThread().getContextClassLoader());
        System.out.println("ServiceLoader的加载器" + ServiceLoader.class.getClassLoader());
    }
}
