package com.example.threadprint;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PrintNumber {


    private final ReentrantReadWriteLock.WriteLock mLock;
    private final Condition condition1;
    private final Condition condition2;

    public PrintNumber() {
        this.mLock = new ReentrantReadWriteLock().writeLock();
        condition1 = mLock.newCondition();
        condition2 = mLock.newCondition();
    }

    private AtomicInteger count = new AtomicInteger(0);

    public void print1() {

        while (count.get() < 100) {
            try {
                mLock.lock();
                System.out.println(Thread.currentThread().getName() + ":" + 1 + "   " + count);
                count.set(count.get() + 1);
                condition2.signalAll();
                condition1.await();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mLock.unlock();
            }
        }
        System.out.println("end PRINT 111111111111111111111");
        mLock.lock();
        condition2.signalAll();
        condition1.signalAll();
        mLock.unlock();
    }

    public void print2() {

        while (count.get() < 100) {
            try {
                mLock.lock();
                System.out.println(Thread.currentThread().getName() + ":" + 2 + "  xxx " + count);
                count.set(count.get() + 1);
                condition1.signalAll();
                condition2.await();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mLock.unlock();
            }
        }
        System.out.println("end PRINT 222222222222222222222222");
        mLock.lock();
        condition1.signalAll();
        condition2.signalAll();
        mLock.unlock();
    }

}
