package com.example.pengxiaolve.databasedemo.xmlparser;

import android.util.Xml;

import com.example.pengxiaolve.databasedemo.module.Book;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by pengxiaolve on 16/5/28.
 */
public class PullParser implements ParserInterface{
    @Override
    public ArrayList<Book> parser(InputStream inputStream) {
        ArrayList<Book> books = null;
        Book book = null;
        int eventType = 0;

        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(inputStream, "UTF-8");
            eventType = parser.getEventType();
        }catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    books = new ArrayList<Book>();
                    break;
                case XmlPullParser.START_TAG:
                    if (parser.getName().equals("book")) {
                        book = new Book();
                    }else if (parser.getName().equals("id")) {
                        //parser当前指向标签，若想获取内容需要向下移动一次
                        parserNext(parser);
                        book.setmId(Integer.parseInt(parser.getText()));
                    }else if (parser.getName().equals("name")) {
                        parserNext(parser);
                        book.setmName(parser.getText());
                    }else if (parser.getName().equals("price")) {
                        parserNext(parser);
                        book.setmPrice(Float.parseFloat(parser.getText()));
                    }
                    else {

                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (parser.getName().equals("book")) {
                        books.add(book);
                    }
                    break;
            }

            eventType = parserNext(parser);
        }

        return books;
    }

    private int parserNext(XmlPullParser parser) {
        int eventType = -1;

        try {
            eventType = parser.next();
        }catch (XmlPullParserException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return eventType;
    }
}
