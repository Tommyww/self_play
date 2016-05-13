package com.study.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangwei on 16/5/11.
 */
public class TestLock {

    public int inc = 0;

    Lock lock = new ReentrantLock();

    public void increase() {
        lock.lock();
        try{
            inc ++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final TestLock test = new TestLock();
        for (int i = 0; i < 10; i ++) {
            new Thread() {
                public void run(){
                    for (int j = 0; j < 10; j ++) {
                        test.increase();
                    }
                }

            }.start();
        }

        System.out.println(test.inc);
    }
}
