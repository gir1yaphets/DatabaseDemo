package com.example.pengxiaolve.databasedemo.module;

/**
 * Created by pengxiaolve on 16/5/30.
 */
public class AndroidPhone extends Phone {
    @Override
    public void getPhoneType() {
        System.out.println("This is an android phone");
    }

    @Override
    public void inComing() {
        super.inComing();
    }
}
