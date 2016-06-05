package com.example.pengxiaolve.databasedemo.module;

/**
 * Created by pengxiaolve on 16/5/28.
 */
public class Book {
    private int mId;
    private String mName;
    private float mPrice;

    public Book() {
    }

    public Book(int mId, String mName, float mPrice) {
        this.mId = mId;
        this.mName = mName;
        this.mPrice = mPrice;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmPrice(float mPrice) {
        this.mPrice = mPrice;
    }

    public int getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public float getmPrice() {
        return mPrice;
    }
}
