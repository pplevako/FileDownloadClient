package com.filedownload.client.ui;

import com.filedownload.client.net.FileResource;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by renpika on 12/27/13.
 */
public class FileDownloadModel {
    private FileResource resource;

    public FileDownloadModel() {
        this.resource = new FileResource();
    }

    public File requestFile(URL url, File file) throws IOException {
        return this.resource.getFile(url, file);
    }
}
