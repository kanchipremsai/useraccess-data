package com.example.useraccess.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.useraccess.model.FileInfo;
import com.example.useraccess.message.ResponseMessage;
import com.example.useraccess.service.FilesStorageService;
@RestController
public class UserAccessController {

    Logger logger = LoggerFactory.getLogger(UserAccessController.class);
    @Autowired
    FilesStorageService storageService;

    @PostMapping("/userdata")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("id") String id) {
        try {

            FileInfo filedata = new FileInfo(id, file.getOriginalFilename() , file.getBytes());
            String fileName = filedata.getName();
            logger.info("#### Request File Name : --> {}", fileName);
            if(fileName.endsWith(".pdf")) {
                storageService.save(filedata);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Userdata uploaded successfully!"));
            }
            else {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Userdata file is not a PDF file"));
            }
        } catch (Exception e) {
            logger.error("ERROR: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Could not upload the file"));
        }
    }

    @GetMapping("/getUserData")
    public ResponseEntity<Object[]> getListUsers() {
        logger.info("### List all users");
        Object[] users = storageService.getAllUsers();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @DeleteMapping("/deleteUserData/{id}")
    @ResponseBody
    public ResponseEntity<ResponseMessage> getDeleteUser(@PathVariable String id) {
        String message = "";
        boolean status = storageService.deleteUserdata(id);
        if(status) {
            message = "User data successfully deleted!";
        } else {
            message = "User data not existed with the id: " + id;
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    }
}