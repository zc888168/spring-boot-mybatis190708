package com.example.threadprint;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PringTest {
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    private final ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
    private Condition condition1 = writeLock.newCondition();
    private Condition condition2 = writeLock.newCondition();
    private Condition condition3 = writeLock.newCondition();

    public static void main(String[] args) {
        PringTest pringTest = new PringTest();
        pringTest.printNum();
    }

    private void printNum() {

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        writeLock.lock();
                        if (atomicInteger.incrementAndGet() > 100) {
                            break;
                        }

                        System.out.println(Thread.currentThread().getName() + " " + atomicInteger.get());
                        condition2.signalAll();
                        condition1.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (writeLock.isHeldByCurrentThread())
                            writeLock.unlock();
                    }
                }
                System.out.println("threadA is over");
                writeLock.lock();
                condition1.signalAll();
                condition2.signalAll();
                condition3.signalAll();
                writeLock.unlock();
                System.out.println("threadA is over no longer");
            }
        }, "threadA");
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        writeLock.lock();
                        if (atomicInteger.incrementAndGet() > 100) {
                            break;
                        }

                        System.out.println("B ==== " + Thread.currentThread().getName() + " " + atomicInteger.get());
                        condition1.signalAll();
                        System.out.println(" condition2.await() before" + atomicInteger.get());
                        condition2.await();
                        System.out.println(" condition2.await() after");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (writeLock.isHeldByCurrentThread())

                            writeLock.unlock();
                    }
                }
                System.out.println("threadB is over");
                writeLock.lock();
                condition2.signalAll();
                condition1.signalAll();
                condition3.signalAll();
                writeLock.unlock();

                System.out.println("threadB is over no longer");
            }
        }, "threadB");
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        writeLock.lock();
                        if (atomicInteger.incrementAndGet() > 100) {
                            break;
                        }

                        System.out.println("C ========== " + Thread.currentThread().getName() + " " + atomicInteger.get());

                        condition1.signalAll();
                        condition3.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (writeLock.isHeldByCurrentThread())

                            writeLock.unlock();
                    }
                }
                System.out.println("threadC is over");
                writeLock.lock();
                condition1.signalAll();
                condition2.signalAll();
                condition3.signalAll();
                writeLock.unlock();
                System.out.println("threadC is over no longer");
            }
        }, "threadC");
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
