package com.filedownload.client.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/**
 * Created by renpika on 12/27/13.
 */
public class FilesListController {
    private Main view;
    private FilesListModel filesListModel;
    private FilesListWorker filesListWorker;

    public FilesListController(Main view, FilesListModel filesListModel) {
        this.filesListModel = filesListModel;
        this.view = view;
        view.addRefreshListAction(new RefreshAction());
        this.filesListWorker = new FilesListWorker();
        this.filesListWorker.execute();
    }

//    private void refreshList() {
//
//    }

    private class RefreshAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            filesListWorker = new FilesListWorker();
            filesListWorker.execute();
        }
    }

    private class FilesListWorker extends SwingWorker<ArrayList<String>, Void> {

        public FilesListWorker() {
        }
        @Override
        protected ArrayList<String> doInBackground() throws Exception {
            return filesListModel.requestList();
        }

        @Override
        protected void done() {
            try {
                ArrayList<String> list = get();
                view.setFilesList(list);
            } catch (CancellationException e) {
                //TODO
                e.printStackTrace();
            } catch (InterruptedException e) {
                //TODO
                e.printStackTrace();
            } catch (ExecutionException e) {
                //TODO
                e.printStackTrace();
            }
        }
    }

}
