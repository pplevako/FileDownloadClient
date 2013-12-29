package com.filedownload.client.net;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by renpika on 12/26/13.
 */
public class Resource {
    private ConnectionConfig config;
    private URL url;

    public Resource(URL url) {
        this(url, new DefaultConfig());
    }

    public Resource(URL url, ConnectionConfig config) {
        this.config = config;
        this.url = url;
    }

    public Response get() throws IOException {
        HttpURLConnection c = (HttpURLConnection) this.url.openConnection();
        c.setRequestMethod("GET");
        c.setRequestProperty("Content-length", "0");
//            c.setUseCaches(false);
//            c.setAllowUserInteraction(false);
//            c.setConnectTimeout(timeout);
//            c.setReadTimeout(timeout);
        c.connect();
        int status = c.getResponseCode();

        switch (status) {
            case HttpURLConnection.HTTP_OK:
            case HttpURLConnection.HTTP_CREATED:
            case HttpURLConnection.HTTP_PARTIAL:
                return new Response(c, status, c.getInputStream());
        }
        return null;
    }

    public static class DefaultConfig implements ConnectionConfig {

        @Override
        public void configure(HttpURLConnection c) {
            //do nothing
        }
    }
}
