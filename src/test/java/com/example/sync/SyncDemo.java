package com.example.sync;

public class SyncDemo {
    private Object obj = new Object();

    public static void main(String[] args) {
        SyncDemo syncDemo = new SyncDemo();
        syncDemo.print1();
        syncDemo.print2();
    }

    private synchronized void print1() {
        System.out.println("javap -c -p -v SyncDemo");

    }

    private void print2() {
        synchronized (obj) {
            System.out.println("javap -c -p SyncDemo");
        }
    }
}
