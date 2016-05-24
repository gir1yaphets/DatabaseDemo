package com.example.pengxiaolve.databasedemo;

import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.pengxiaolve.databasedemo.db.Constant;
import com.example.pengxiaolve.databasedemo.fragment.ContactFragment;
import com.example.pengxiaolve.databasedemo.provider.ContactProvider;

public class MainActivity extends AppCompatActivity {

    private Button mInsertButton;
    private Button mdeleteButton;
    private Button mQueryButton;

    private ContactFragment mContactFragment;

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
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.CONTACT_ID, 1);
        contentValues.put(Constant.NAME, "Kite");
        contentValues.put(Constant.PHONE, "110");

        Uri uri = ContactProvider.CONTENT_URI;
        getContentResolver().insert(uri, contentValues);
    }

    private void delete() {
        Uri uri = ContentUris.withAppendedId(ContactProvider.CONTENT_URI, 1);
        getContentResolver().delete(uri, null, null);
    }

    private void query() {
        mContactFragment = (ContactFragment) getFragmentManager().findFragmentById(R.id.contactfragment);
        getLoaderManager().restartLoader(0, null, mContactFragment.mCallbacks);
    }
}
