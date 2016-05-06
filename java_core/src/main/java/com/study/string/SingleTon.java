package com.study.string;

import javax.swing.plaf.synth.SynthOptionPaneUI;

/**
 * Created by wangwei on 16/5/6.
 */
public class SingleTon {

    private static SingleTon instance = null;
    private SingleTon(){}
    public static SingleTon getInstance(){
        if (instance == null) {
            synchronized(SingleTon.class){
                instance = new SingleTon();
            }
        }
        return instance;
    }
}
class TestSingleTon{
    public static void main(String[] args) {
        SingleTon.getInstance();
        System.out.println("初始化完成");
    }
 }
