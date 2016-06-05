package com.example.pengxiaolve.databasedemo.fragment;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

import com.example.pengxiaolve.databasedemo.R;
import com.example.pengxiaolve.databasedemo.activity.MainActivity;
import com.example.pengxiaolve.databasedemo.db.Constant;
import com.example.pengxiaolve.databasedemo.provider.ContactProvider;

/**
 * Created by pengxiaolve on 16/5/21.
 */
public class ContactFragment extends ListFragment {

    private Context mContext;
    private SimpleCursorAdapter mAdapter;
    private Handler mHandler = new ClickListenerHandler();
    private int mContact_ID = 1;

    public static final int MESSAGE_INSERT = 0;
    public static final int MESSAGE_QUERY = 1;
    public static final int MESSAGE_DELETE = 2;

    private String[] projection = new String[] {
            Constant._ID,
            Constant.CONTACT_ID,
            Constant.NAME,
            Constant.PHONE
    };

    public interface ActivityCallbacks {
        void setHandler(Handler handler);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ContactFragment.ActivityCallbacks) {
            MainActivity activity = (MainActivity) context;
            activity.setHandler(mHandler);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();

        mAdapter = new SimpleCursorAdapter(mContext, R.layout.item_view, null,
                new String[] {Constant.NAME, Constant.PHONE},
                new int[]{R.id.name, R.id.phone}, 0);

        getLoaderManager().initLoader(0, null, mCallbacks);
        setListAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public LoaderManager.LoaderCallbacks<Cursor> mCallbacks = new LoaderManager.LoaderCallbacks<Cursor> () {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {

            return new CursorLoader(mContext,
                    ContactProvider.CONTENT_URI,
                    projection,
                    null,
                    null,
                    Constant.CONTACT_ID + " ASC"
            );
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            mAdapter.changeCursor(data);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            mAdapter.changeCursor(null);
        }
    };

    private class ClickListenerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_INSERT:
                    handleInsert(msg);
                    break;
                case MESSAGE_QUERY:
                    handleQuery(msg);
                    break;
                case MESSAGE_DELETE:
                    handleDelete(msg);
                    break;
                default:
                    break;
            }
        }
    }

    private void handleInsert(Message msg) {
        if (msg.obj != null) {
            Intent intent = (Intent) msg.obj;
            Bundle args = intent.getBundleExtra("contact");
            String name = args.getString("name");
            String phone = args.getString("phone");

            if (!TextUtils.isEmpty(name)) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(Constant.CONTACT_ID, mContact_ID);
                contentValues.put(Constant.NAME, name);
                contentValues.put(Constant.PHONE, phone);

                Uri uri = ContactProvider.CONTENT_URI;
                mContext.getContentResolver().insert(uri, contentValues);

                mContact_ID += 1;
            }
        }
    }
    private void handleDelete(Message msg) {
        Uri uri = ContentUris.withAppendedId(ContactProvider.CONTENT_URI, mContact_ID);
        mContext.getContentResolver().delete(uri, null, null);
    }

    private void handleQuery(Message msg) {
        getLoaderManager().restartLoader(0, null, mCallbacks);
    }
}
