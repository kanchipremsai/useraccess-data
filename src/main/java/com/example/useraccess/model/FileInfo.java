package com.example.useraccess.model;

public class FileInfo {
    private String id;
    private String name;
    private byte[] data;

    public FileInfo(String id, String name, byte[] data) {
        this.id = id;
        this.name = name;
        this.data = data;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setUrl(byte[] data) {
        this.data = data;
    }
}
