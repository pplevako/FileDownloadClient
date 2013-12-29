package com.filedownload.client.ui;

import com.filedownload.client.net.JSONResource;
import com.google.gson.Gson;

import javax.swing.event.SwingPropertyChangeSupport;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by renpika on 12/27/13.
 */
public class FilesListModel {

    public enum Properties {
        LIST
    }

    private SwingPropertyChangeSupport propChangeSupport =
            new SwingPropertyChangeSupport(this);

    private ArrayList<String> list;

    private URL url;
    public FilesListModel(URL url) {
        this.url = url;
        list = new ArrayList<String>();
    }

    public ArrayList<String> getList() {
       return list;
    }

    public ArrayList<String> requestList() throws IOException {
        JSONResource resource = new JSONResource(url);
        String data = resource.getJSON();
        Gson gson = new Gson();
        ArrayList<String> newList = gson.fromJson(data, ArrayList.class);
        //TODO
        //propChangeSupport.firePropertyChange(Properties.LIST.toString(), this.list, newList);
        this.list = newList;
        return list;
    }

}
