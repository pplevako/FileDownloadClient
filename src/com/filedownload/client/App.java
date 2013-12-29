package com.filedownload.client;

import com.filedownload.client.ui.*;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by renpika on 12/30/13.
 */
public class App {

    public static void main(String[] args) throws MalformedURLException {
        JFrame frame = new JFrame("Main");
        Main view = new Main(frame);
        Config config = Config.getDefault();

        URL url = new URL("http", config.address, config.port, config.filesRoute);
        FilesListModel model = new FilesListModel(url);
        FileDownloadModel fileDownloadModel = new FileDownloadModel();
        FileDownloadController c1 = new FileDownloadController(view, fileDownloadModel, config);
        FilesListController c = new FilesListController(view, model);

        view.setVisible(true);

        //c.showView;
    }
}
