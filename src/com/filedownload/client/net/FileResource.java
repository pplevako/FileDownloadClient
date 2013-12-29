package com.filedownload.client.net;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * Created by renpika on 12/26/13.
 */
public class FileResource {

    private CacheFileProvider fileProvider;

    public static class CacheFileProvider {
        public File create(File file) {
            File temp = new File(file.getAbsoluteFile() + ".download");
            return temp;
        }
    }

    public FileResource() {
        this.fileProvider = new CacheFileProvider();
    }

    public File getFile(URL url, File file) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        File cacheFile = fileProvider.create(file);

        long downloaded = cacheFile.length();

        if (downloaded > 0) {
            Date date = new Date(cacheFile.lastModified());
            connection.setRequestProperty("If-Range", Utils.getHTTPDate(date));
            connection.setRequestProperty("Range", "bytes=" + downloaded + "-");
        }

        FileOutputStream fos = null;

        int status = connection.getResponseCode();
        if (status == HttpURLConnection.HTTP_OK) {
//            String s = connection.getHeaderField("Content-Length");
            fos = new FileOutputStream(cacheFile);
        }

        if (status == HttpURLConnection.HTTP_PARTIAL) {
            fos = new FileOutputStream(cacheFile, true);
        }
        //TODO Check for 404

        BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
        BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
        try {

            byte[] data = new byte[1024];
            int x = 0;

            boolean interrupted = false;
            while ((x = in.read(data, 0, 1024)) >= 0) {
                bout.write(data, 0, x);
                downloaded += x;
                if (Thread.currentThread().isInterrupted()) {
                    interrupted = true;
                    break;
                }
            }

            if (!interrupted)
                cacheFile.renameTo(file);

        } finally {
            in.close();
            bout.close();
        }

        return cacheFile;
    }

}
