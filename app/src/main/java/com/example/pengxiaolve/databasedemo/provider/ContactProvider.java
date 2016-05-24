package com.example.pengxiaolve.databasedemo.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.pengxiaolve.databasedemo.db.Constant;
import com.example.pengxiaolve.databasedemo.db.ContactDb;

/**
 * Created by pengxiaolve on 16/5/18.
 */
public class ContactProvider extends ContentProvider {

    private SQLiteOpenHelper mSQLiteOpenHelper;

    private static final String CONTENT_AUTHORITY = "com.example.pengxiaolue.databasedemo";
    private static final Uri BASE_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(Constant.TABLE_NAME).build();

    private static final String ITEM_PATH = "contacts/#";
    private static final String DIR_PATH = "contacts";

    private static final int MATCH_ITEM = 0;
    private static final int MATCH_DIR = 1;

    private static final String ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.contact.item";
    private static final String DIR_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.contact.dir";

    private static final String[] MIME_TYPES = {ITEM_TYPE, DIR_TYPE};

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static{
        sUriMatcher.addURI(CONTENT_AUTHORITY, ITEM_PATH, MATCH_ITEM);
        sUriMatcher.addURI(CONTENT_AUTHORITY, DIR_PATH, MATCH_DIR);
    }

    @Override
    public boolean onCreate() {
        mSQLiteOpenHelper = new ContactDb(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mSQLiteOpenHelper.getReadableDatabase();

        Cursor cursor = db.query(getTableName(uri),//table name
                                 projection,        //columns
                                 selection,
                                 selectionArgs,
                                 null,              //group by
                                 null,              //having
                                 sortOrder);        //order by

        getContext().getContentResolver().notifyChange(uri, null);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        int match = sUriMatcher.match(uri);
        if (match < MIME_TYPES.length) {
            return MIME_TYPES[match];
        }
        else {
            throw new UnsupportedOperationException("Unknown Uri" + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        if (match == MATCH_DIR) {
            long rowId = db.insert(getTableName(uri), null, values);
            Uri retUri = ContentUris.withAppendedId(uri, rowId);
            getContext().getContentResolver().notifyChange(uri,null);
            return retUri;
        } else {
            throw new UnsupportedOperationException("UnSupported Uri" + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        int count = 0;

        if (match == MATCH_ITEM) {
            long deleteId = ContentUris.parseId(uri);
            count = db.delete(getTableName(uri), Constant.CONTACT_ID + "=" +deleteId, null);
        }
        else if (match == MATCH_DIR) {

        }
        else {

        }

        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    private String getTableName(Uri uri) {
        String tableName = uri.getPath();
        if (sUriMatcher.match(uri) == 0) {
            tableName = tableName.substring(1);
            int endIndex = tableName.indexOf("/");
            tableName = tableName.substring(0, endIndex);
        }
        else {
            tableName = tableName.replace("/", "");
        }

        return tableName;
    }
}
