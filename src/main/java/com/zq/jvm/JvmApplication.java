package com.zq.jvm;

import com.zq.classloader.MyClassLoader;
import com.zq.classloader.Test;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

public class JvmApplication {
    public static void main2(String[] args) throws ClassNotFoundException {
        MyClassLoader myClassLoader = new MyClassLoader("D:\\workspace\\jvm\\target\\classes\\");
        Class<?> clazz = myClassLoader.loadClass("com.zq.classloader.Test");
        System.out.println(clazz.getClassLoader());
        System.out.println(clazz.getClassLoader().getParent());
        System.out.println(clazz.getClassLoader().getParent().getParent());
        System.out.println(clazz.getClassLoader().getParent().getParent().getParent());
    }

    private int a;
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            byte[] bytes = new byte[1024 * 1024];
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }
}
