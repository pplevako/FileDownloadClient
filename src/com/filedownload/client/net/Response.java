package com.filedownload.client.net;

import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by renpika on 12/26/13.
 */
public class Response {

    public InputStream inputStream;
    public HttpURLConnection connection;
    public int status;

//    public boolean OK() {
//        return connection.getResponseCode() == 200;
//    }
//
//    public InputStream getInputStream() {
//        return connection.getInputStream();
//    }

    public Response(HttpURLConnection connection, int status, InputStream inputStream) {
        this.inputStream = inputStream;
        this.connection = connection;
        this.status = status;
    }
}
