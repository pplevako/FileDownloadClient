package com.filedownload.client.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by renpika on 12/26/13.
 */
public class JSONResource {
    private Resource res;

//    private class JSONConfig implements ConnectionConfig {
//
//        @Override
//        public void configure(HttpURLConnection c) {
//
//        }
//    }

    public JSONResource(URL url) {
        this.res = new Resource(url);
    }

    public String getJSON() throws IOException {
        Response r = res.get();
        //TODO: improve this code
        if (r == null)
            return null;

        InputStream stream = res.get().inputStream;
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        return sb.toString();
    }
}
