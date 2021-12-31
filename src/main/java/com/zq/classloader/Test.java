package com.zq.classloader;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Test {
    private static int state = 0;
    private static long offset;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        offset = unsafe.staticFieldOffset(Test.class.getDeclaredField("state"));
        unsafe.compareAndSwapInt(Test.class, offset, 1, 1);
        System.out.println(state);
    }
}
