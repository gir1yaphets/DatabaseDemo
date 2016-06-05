package com.example.pengxiaolve.databasedemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.pengxiaolve.databasedemo.R;
import com.example.pengxiaolve.databasedemo.fragment.ContactFragment;
import com.example.pengxiaolve.databasedemo.module.AndroidPhone;

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

        AndroidPhone androidPhone = new AndroidPhone();
        androidPhone.inComing();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {
            Intent intent = new Intent(this, AddContactActivity.class);
            startActivityForResult(intent, 1);
        } else if (item.getItemId() == R.id.menu_save) {
            Intent intent = new Intent(this, BookActivity.class);
            startActivity(intent);
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            insert(data);
        }
    }

    private void insert(Intent data) {
        Message msg = new Message();
        msg.what = ContactFragment.MESSAGE_INSERT;
        msg.obj = data;
        mHandler.sendMessage(msg);
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
