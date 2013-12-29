package com.filedownload.client.ui;

import com.filedownload.client.Config;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/**
 * Created by renpika on 12/27/13.
 */
public class FileDownloadController {

    private FileDownloadWorker fileDownloadWorker;
    private FileDownloadModel fileDownloadModel;
    private Main view;
    private boolean isDownloading;
    private Config config;


    public FileDownloadController(Main view, FileDownloadModel fileDownloadModel, Config config) {
        this.fileDownloadModel = fileDownloadModel;
        this.view = view;
        this.isDownloading = false;
        this.config = config;
        view.addDownloadAction(new DownloadAction());
    }

    private void startDownload(URL url, File file) {
        fileDownloadWorker = new FileDownloadWorker(url, file);
        fileDownloadWorker.execute();
        view.setDownloadStarted();
        isDownloading = true;
    }

    private void stopDownload() {
        if (fileDownloadWorker != null)
            fileDownloadWorker.cancel(true);
    }

    private class DownloadAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isDownloading) {
                stopDownload();
            } else {
                JFileChooser fileChooser = new JFileChooser();
                String selected = view.getSelectedFile();
                fileChooser.setSelectedFile(new File(selected));
                int rVal = fileChooser.showSaveDialog(view.getWindow());
                if (rVal == JFileChooser.APPROVE_OPTION) {

                    //TODO Protocol.getFileURL(fileName)
                    URL url = null;
                    try {
                        URI newUri = new URI("http", null, config.address, config.port, config.filesRoute + "/" + selected, null, null);
                        url = newUri.toURL();

                        startDownload(url, fileChooser.getSelectedFile());

                        //TODO ignored exceptions
                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                    } catch (URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                }
                if (rVal == JFileChooser.CANCEL_OPTION) {
                }
            }
        }
    }


    private class FileDownloadWorker extends SwingWorker<File, Void> {

        private URL url;
        private File file;

        public FileDownloadWorker(URL url, File file) {
            this.url = url;
            this.file = file;
        }

        @Override
        protected File doInBackground() throws Exception {
            return fileDownloadModel.requestFile(url, file);
        }

        @Override
        protected void done() {
            try {
                File download = get();
                view.setDownloadStatus("Complete");
            } catch (CancellationException e) {
                view.setDownloadStatus("Paused");
                //e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                //e.printStackTrace();
                view.setDownloadStatus("Error");
            } finally {
                view.setDownloadStopped();
                isDownloading = false;
            }
        }
    }

}
