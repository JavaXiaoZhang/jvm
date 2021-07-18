package com.zq;

import com.zq.classloader.MyClassLoader;

public class JvmApplication {
    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader myClassLoader = new MyClassLoader("D:\\workspace\\jvm\\target\\classes\\");
        Class<?> clazz = myClassLoader.loadClass("com.zq.classloader.Test");
        System.out.println(clazz.getClassLoader());
    }
}
