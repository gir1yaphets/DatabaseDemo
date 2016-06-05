package com.example.pengxiaolve.databasedemo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.pengxiaolve.databasedemo.R;
import com.example.pengxiaolve.databasedemo.http.HttpUtils;
import com.example.pengxiaolve.databasedemo.module.Book;
import com.example.pengxiaolve.databasedemo.xmlparser.PullParser;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pengxiaolve on 16/5/28.
 */
public class BookActivity extends AppCompatActivity implements Runnable{

    private static final String TAG = "BookActivity";
    private static final String URL = "http://192.168.1.3:8080/Book/book.xml";
    private HttpHandler mHandler;
    private SimpleAdapter mAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list);

        mListView = (ListView) findViewById(R.id.booklist);

        System.out.println("onCreate current thread id--->" + Thread.currentThread().getId());
        HandlerThread handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        mHandler = new HttpHandler(handlerThread.getLooper());

        mHandler.post(this);
    }

    class HttpHandler extends Handler {
        public HttpHandler (Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x01) {
                createBookList((ArrayList<Book>) msg.obj);
            }
        }
    }

    @Override
    public void run() {
        System.out.println("run current thread id--->" + Thread.currentThread().getId());
        try {
            URL url = new URL(URL);
            InputStream inputStream = HttpUtils.httpConnectGetInputStream(url);
            ArrayList<Book> books = new PullParser().parser(inputStream);
            Message msg = new Message();
            msg.what = 0x01;
            msg.obj = books;
            mHandler.sendMessage(msg);
        }catch (Exception e) {

        }
    }

    private void createBookList(ArrayList<Book> books) {
        List<Map<String, Object>> list= new ArrayList<Map<String, Object>>();

        for (Book book : books) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("Book", book.getmName());
            map.put("Price", book.getmPrice());
            list.add(map);
        }

        mAdapter = new SimpleAdapter(this, list,
                R.layout.book_item,
                new String[] {"Book", "Price"},
                new int[] {R.id.bookname, R.id.price});

        mListView.setAdapter(mAdapter);
    }
}
