package com.example.pengxiaolve.databasedemo.db;

import android.provider.BaseColumns;

/**
 * Created by pengxiaolve on 16/5/17.
 */
public class Constant implements BaseColumns{
    public static final String TABLE_NAME = "contacts";

    public static final int DB_VERSION = 1;

    public static final String DB_NAME = "contact.db";

    public static final String NAME = "name";

    public static final String PHONE = "phone";

    public static final String CONTACT_ID = "contact_id";

    public static final String CREATE_TABLE = "CREATE TABLE ";

    public static final String TYPE_TEXT = " TEXT";

    public static final String TYPE_INTEGER = " INTEGER";

    public static final String COMMA_SEP = ",";

    public static final String COLUM_START = " (";

    public static final String COLUM_END = ")";
}
