package com.example.pengxiaolve.databasedemo.fragment;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

import com.example.pengxiaolve.databasedemo.R;
import com.example.pengxiaolve.databasedemo.db.Constant;
import com.example.pengxiaolve.databasedemo.provider.ContactProvider;

/**
 * Created by pengxiaolve on 16/5/21.
 */
public class ContactFragment extends ListFragment {

    private Context mContext;
    private SimpleCursorAdapter mAdapter;
    private Handler mHandler;

    private String[] projection = new String[] {
            Constant._ID,
            Constant.CONTACT_ID,
            Constant.NAME,
            Constant.PHONE
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
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
}
