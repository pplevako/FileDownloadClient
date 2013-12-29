package com.filedownload.client.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Collection;

/**
 * Created by renpika on 12/26/13.
 */
public class Main {
    private JList filesList;
    private JPanel jpanel1;
    private JButton refreshListButton;
    private JButton downloadFileButton;
    private JLabel downloadStatus;
    private JFrame frame;

    public void setFilesList(Collection<String> values) {
        DefaultListModel model = new DefaultListModel<String>();
        for(String s : values){
            model.addElement(s);
        }
        filesList.setModel(model);
        filesList.setSelectedIndex(0);
    }

    public void setDownloadStatus(String text) {
        downloadStatus.setText(text);
    }
    public void setDownloadStarted() {
        downloadFileButton.setText("Stop");
    }

    public void setDownloadStopped() {
        downloadFileButton.setText("Start");
    }

    public void setDownloadButtonText(String text) {
        downloadFileButton.setText(text);
    }

    public void addRefreshListAction(ActionListener action) {
        refreshListButton.addActionListener(action);
    }

    public void addDownloadAction(ActionListener action) {
        downloadFileButton.addActionListener(action);
    }

    public String getSelectedFile() {
        return filesList.getSelectedValue().toString();
    }

    public Window getWindow() {
        return frame;
    }

    public void setVisible(boolean visible) {
        frame.setContentPane(jpanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Main(JFrame frame) {
        this.frame = frame;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
