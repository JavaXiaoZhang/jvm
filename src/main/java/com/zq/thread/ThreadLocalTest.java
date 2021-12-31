package com.zq.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalTest {
    private final static ThreadLocal threadlocal = new ThreadLocal();

    public static void main(String[] args) {
        /*threadlocal.set("aa");
        threadlocal.get();
        threadlocal.remove();*/

        Map map = new HashMap();
        /*new Thread(()->{
            map.put("1", 1);
        }).start();
        new Thread(()->{
            map.put("1", 2);
        }).start();*/


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
                new MyThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        threadPoolExecutor.execute(()->{
            map.put("1", 1);
            System.out.println(Thread.currentThread().getName());
        });
        threadPoolExecutor.execute(()->{
            map.put("1", 2);
            System.out.println(2);
            System.out.println(Thread.currentThread().getName());
        });
        while (threadPoolExecutor.getCompletedTaskCount() < 2) {

        }
        System.out.println("finish");
        try {
            threadPoolExecutor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPoolExecutor.shutdown();
        threadPoolExecutor.shutdownNow();
    }

    static class MyThreadFactory implements ThreadFactory{
        AtomicInteger threadNumber = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(Thread.currentThread().getThreadGroup(), r,
                    "自定义线程" + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
