package com.example.threadprint;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("hello world");

        PrintNumber number = new PrintNumber();
        new Thread(number::print1, "ThreadA").start();
        new Thread(number::print2, "ThreadA").start();
    }

}
