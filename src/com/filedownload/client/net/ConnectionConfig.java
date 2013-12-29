package com.filedownload.client.net;

import java.net.HttpURLConnection;

/**
 * Created by renpika on 12/26/13.
 */
public interface ConnectionConfig {
    void configure(HttpURLConnection c);
}
