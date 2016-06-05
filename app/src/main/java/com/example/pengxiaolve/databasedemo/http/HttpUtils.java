package com.example.pengxiaolve.databasedemo.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by pengxiaolve on 16/5/28.
 */
public class HttpUtils {
    private static InputStream inputStream = null;

    public static InputStream httpConnectGetInputStream (URL url) {

        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return inputStream;
    }
}
