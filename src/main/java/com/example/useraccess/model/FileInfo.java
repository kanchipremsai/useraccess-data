package com.example.useraccess.model;

import lombok.Getter;

@Getter
public class FileInfo {

    private String id;
    private String name;
    private byte[] data;

    public FileInfo(String id, String name, byte[] data) {
        this.id = id;
        this.name = name;
        this.data = data;
    }
}
