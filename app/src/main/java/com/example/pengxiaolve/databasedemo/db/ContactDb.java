package com.example.pengxiaolve.databasedemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pengxiaolve on 16/5/17.
 */
public class ContactDb extends SQLiteOpenHelper {

    private static final String SQLCREATE =
              Constant.CREATE_TABLE + Constant.TABLE_NAME + Constant.COLUM_START
            + Constant._ID + " INTEGER PRIMARY KEY" + Constant.COMMA_SEP
            + Constant.CONTACT_ID + Constant.TYPE_INTEGER + Constant.COMMA_SEP
            + Constant.NAME + Constant.TYPE_TEXT + Constant.COMMA_SEP
            + Constant.PHONE + Constant.TYPE_TEXT + Constant.COLUM_END;

    public ContactDb(Context context) {
        super(context, Constant.DB_NAME, null, Constant.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLCREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
