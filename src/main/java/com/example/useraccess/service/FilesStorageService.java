package com.example.useraccess.service;

import com.example.useraccess.model.FileInfo;

public interface FilesStorageService {

    public void save(FileInfo userdata);

    public Object[] getAllUsers();

    public boolean deleteUserdata(String id);

}
