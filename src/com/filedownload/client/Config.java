package com.filedownload.client;

/**
 * Created by renpika on 12/30/13.
 */
public class Config {
    public int port;
    public String address;
    public String filesRoute;

    public Config(String address, int port, String filesRoute) {
        this.address = address;
        this.port = port;
        this.filesRoute = filesRoute;
    }

    public static Config getDefault() {
        return new Config("127.0.0.1", 8000, "/api/files");
    }
}
