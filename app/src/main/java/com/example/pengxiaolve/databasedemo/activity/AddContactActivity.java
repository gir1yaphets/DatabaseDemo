package com.example.pengxiaolve.databasedemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pengxiaolve.databasedemo.R;

/**
 * Created by pengxiaolve on 16/5/26.
 */
public class AddContactActivity extends AppCompatActivity {
    private Button mAddButton;
    private EditText mEditName;
    private EditText mEditPhone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontact);

        mAddButton = (Button) findViewById(R.id.add);
        mEditName = (EditText) findViewById(R.id.contact_name);
        mEditPhone = (EditText) findViewById(R.id.contact_phone);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle args = new Bundle();
                args.putString("name", mEditName.getText().toString());
                args.putString("phone", mEditPhone.getText().toString());
                intent.putExtra("contact", args);
                setResult(1, intent);
                finish();
            }
        });
    }
}
