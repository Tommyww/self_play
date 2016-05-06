package com.study.string;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangwei on 16/5/6.
 */
public class TestThread {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i ++) {
            Thread1 thread1 = new Thread1();
            Thread r = new Thread(thread1);
            r.start();
            System.out.print("普通：");
            System.out.println(Long.valueOf(1));
        }
    }
}

class Thread1 implements Runnable{

    //这里基本类型也能锁住，因为从cache里读，-128-127直接转int并从cache里读
    //如果一次性全部打印出来，说明没锁住，挨个依次打出来，说明锁住
    @Override
    public void run(){
        synchronized (Long.valueOf(1)) {
            try {
                System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + ":" + 1);
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
