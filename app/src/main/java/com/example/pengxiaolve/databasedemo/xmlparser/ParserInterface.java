package com.example.pengxiaolve.databasedemo.xmlparser;

import com.example.pengxiaolve.databasedemo.module.Book;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by pengxiaolve on 16/5/28.
 */
public interface ParserInterface {
    public ArrayList<Book> parser (InputStream inputStream);
}
