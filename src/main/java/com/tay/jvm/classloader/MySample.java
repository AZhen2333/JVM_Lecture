package com.tay.jvm.classloader;

public class MySample {
    public MySample() {
        System.out.println("MySample is loaded by:" + this.getClass().getClassLoader());
        new MyCat();
        // 步骤二：在 MySample 中引用 MyCat
        System.out.println("from MySample:" + MyCat.class);
    }
}
