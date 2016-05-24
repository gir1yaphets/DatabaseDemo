package com.example.pengxiaolve.databasedemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.pengxiaolve.databasedemo.fragment.ContactFragment;

public class MainActivity extends AppCompatActivity implements ContactFragment.ActivityCallbacks{

    private Button mInsertButton;
    private Button mdeleteButton;
    private Button mQueryButton;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInsertButton = (Button) findViewById(R.id.insertButton);
        mdeleteButton = (Button) findViewById(R.id.deleteButton);
        mQueryButton  = (Button) findViewById(R.id.queryButton);

        mInsertButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                insert();
            }
        });

        mdeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

        mQueryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });
    }

    private void insert() {
        Message msg = new Message();
        msg.what = ContactFragment.MESSAGE_INSERT;
        mHandler.sendMessage(msg);
    }

    private void delete() {
        Message msg = new Message();
        msg.what = ContactFragment.MESSAGE_DELETE;
        mHandler.sendMessage(msg);
    }

    private void query() {
        Message msg = new Message();
        msg.what = ContactFragment.MESSAGE_QUERY;
        mHandler.sendMessage(msg);
    }

    @Override
    public void setHandler(Handler handler) {
        this.mHandler = handler;
    }
}
