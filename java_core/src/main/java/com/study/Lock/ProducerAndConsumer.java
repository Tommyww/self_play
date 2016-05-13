package com.study.Lock;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangwei on 16/5/12.
 */
public class ProducerAndConsumer {

    public static void main(String[] args) {
        Storage storage = new Storage();
        Producter p1 = new Producter(storage, 1);
        Producter p2 = new Producter(storage, 2);
        Producter p3 = new Producter(storage, 3);

        Consumer c1 = new Consumer(storage, 1);
        Consumer c2 = new Consumer(storage, 5);
        p1.start();
        p2.start();
        p3.start();
        new Thread(c1).start();
        new Thread(c2).start();
    }
}

@Data
class Storage{
    private static final int MAX_SIZE = 10;

    private List<Object> list = new ArrayList();

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void produce(int num) {
        while (true) {
            /*synchronized (list) {
                try {
                    while (list.size() + num > MAX_SIZE) {
                        list.wait();
                    }
                    for (int i = 1; i <= num; i ++) {
                        list.add(new Object());
                    }
                    Thread.sleep(1000);
                    System.out.println("生产者生产了" + num + "个,现在仓库中有" + list.size() + "个");
                    list.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/
            lock.lock();
            try {
                while (list.size() + num > MAX_SIZE) {
                    condition.await();
                }
                for (int i = 1; i <= num; i ++) {
                    list.add(new Object());
                }
                Thread.sleep(1000);
                System.out.println("生产者生产了" + num + "个,现在仓库中有" + list.size() + "个");
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public void consume(int num) {
        while (true) {
            /*synchronized (list) {
                try {
                    while (list.size() < num) {
                            list.wait();
                    }
                    for (int i = 1; i <= num; i ++) {
                        list.remove(list.size() - 1);
                    }
                    Thread.sleep(500);
                    System.out.println("消费者消费了" + num + "个，现在仓库中有" + list.size() + "个");
                    list.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/
            lock.lock();
            try {
                while (list.size() < num) {
                    condition.await();
                }
                for (int i = 1; i <= num; i ++) {
                    list.remove(list.size() - 1);
                }
                Thread.sleep(500);
                System.out.println("消费者消费了" + num + "个，现在仓库中有" + list.size() + "个");
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}

@Data
class Producter extends Thread{

    private int num;

    private Storage storage;

    public Producter(Storage storage, int num) {
        this.storage = storage;
        this.num = num;
    }

    public void run() {
        storage.produce(num);
    }
}

class Consumer implements Runnable {

    private int num;
    private Storage storage;

    public Consumer(Storage storage, int num) {
        this.storage = storage;
        this.num = num;
    }

    public void run() {
        storage.consume(num);
    }
}