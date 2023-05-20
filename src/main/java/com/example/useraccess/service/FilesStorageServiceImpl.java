package com.example.useraccess.service;

import java.util.HashMap;
import java.util.Map;
import com.example.useraccess.model.FileInfo;
import org.springframework.stereotype.Service;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

    private final Map<String, FileInfo> userprofiles = new HashMap<>();

    @Override
    public void save(FileInfo userdata) {
        userprofiles.put(userdata.getId(), userdata);
    }

    @Override
    public Object[] getAllUsers() {
        return userprofiles.values().toArray();
    }

    @Override
    public boolean deleteUserdata(String id) {
        boolean status = false;
        FileInfo userdata = userprofiles.remove(id);
        if(userdata != null) {
            status = true;
        }
        return status;
    }
}
