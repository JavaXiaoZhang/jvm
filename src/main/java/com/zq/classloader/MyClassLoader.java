package com.zq.classloader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyClassLoader extends ClassLoader{

    //指定从哪里加载类
    private String classPath;

    public MyClassLoader(String classPath){
        this.classPath = classPath;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        // First, check if the class has already been loaded
        Class<?> c = findLoadedClass(name);
        if (c == null) {
            long t0 = System.nanoTime();
            if (name.startsWith("com.zq")){
                c = findClass(name);
            }else {
                try {
                    if (this.getParent() != null) {
                        c = this.getParent().loadClass(name);
                    }
                } catch (ClassNotFoundException e) {
                    // ClassNotFoundException thrown if class not found
                    // from the non-null parent class loader
                }
            }
        }
        return c;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        // 1.根据类全名获取文件流
        byte[] bytes = loadByte(className);
        // 2.通过API将字节数组转为class对象
        return defineClass(className, bytes, 0, bytes.length);
    }

    private byte[] loadByte(String className) {
        // 1.将类全名转为完整的类路径
        className = className.replaceAll("\\.", "/");
        className = classPath + className + ".class";
        // 2.采用文件字节流读取文件内容，将其转为字节数组
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(className);
            byte[] data = new byte[fileInputStream.available()];
            fileInputStream.read(data);
            return data;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new byte[0];
    }
}
