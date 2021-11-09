package com.zq;

public class StackTest {

    private static int count = 0;

    public static void recursion(){
        count++;
        recursion();
    }

    public static void main(String[] args) {
        try {
            recursion();
        }catch (Throwable e){
            e.printStackTrace();
            System.out.println(count);
        }
    }
}
