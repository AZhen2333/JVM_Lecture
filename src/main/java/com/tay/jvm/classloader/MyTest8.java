package com.tay.jvm.classloader;

import java.util.Random;

/**
 * 当FinalTest中定义常量final x = 3时，查看反编译：iconst_3，并没有主动调用FainlTest类，只是从常量池中调用x
 * 当FinalTest中定义静态变量x = 3时，查看反编译：getstatic，主动调用FainlTest类
 */
public class MyTest8 {
    public static void main(String[] args) {
        // 这里因为x前面有fianl，所以是一个编译期常量，编译后就会放在MyTest8类的常量池中。
        // 编译完后MyTest8和FinalTest类之间就没有任何关系。
        // 因此静态代码块都没有执行，因此FinalTest类都没有被初始化，所以将FinalTest.class删除，代码仍然可以执行。;
        // 通过反编译，助记符iconst_3 表示将整型的数值3从常量池中推送到栈顶;
        System.out.println(FinalTest.x);

        // 这里y值在编译期确定不了，得运行期使用该类。
        // 因此编译后删除该类再运行时会报错:
        // Exception in thread "main" java.lang.NoClassDefFoundError: com/tay/jvm/classloader/FinalTest;
        // 通过反编译得到： “getstatic #5  // Field com/tay/jvm/classloader/FinalTest.y:I”，
        // 其中“FinalTest.y:I”相当于直接引用了FinalTest中的y变量;
        System.out.println(FinalTest.y);
    }
}

class FinalTest {
    public static final int x = 3;
    public static final int y = new Random().nextInt();

    static {
        System.out.println("FinalTest static block");
    }
}
