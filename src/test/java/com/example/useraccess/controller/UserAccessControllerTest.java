package com.example.useraccess.controller;

import com.example.useraccess.message.ResponseMessage;
import com.example.useraccess.model.FileInfo;
import com.example.useraccess.service.FilesStorageService;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.doCallRealMethod;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.useraccess.service.FilesStorageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebMvcTest(UserAccessController.class)
public class UserAccessControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilesStorageServiceImpl storageService;

    private final Map<String, FileInfo> userprofiles = new HashMap<>();

    @BeforeEach
    public void setup() {
        FileInfo user1 = new FileInfo("1234","prem_profile.jpg", new byte[1]);
        FileInfo user2 = new FileInfo("1235","prem_profile.jpg", new byte[1]);
        FileInfo user3 = new FileInfo("1236","prem_profile.jpg", new byte[1]);
        FileInfo user4 = new FileInfo("1237","prem_profile.jpg", new byte[1]);

        userprofiles.put("1234", user1);
        userprofiles.put("1235", user2);
        userprofiles.put("1236", user3);
        userprofiles.put("1237", user4);

        ReflectionTestUtils.setField(this.storageService, "userprofiles", userprofiles);

    }

    @Test
    public void testPostUser() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Test file content".getBytes());
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "4696");

        FileInfo fileInfo = new FileInfo("4696", "test.txt", "Test file content".getBytes());

        mockMvc.perform(multipart("/userdata")
                        .file(file)
                        .params(params))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Userdata uploaded successfully!"));

        doCallRealMethod().when(storageService).save(fileInfo);
    }

    @Test
    public void testGetUsers() throws Exception {

        when(storageService.getAllUsers()).thenCallRealMethod();

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(userprofiles.size()));
    }

    @Test
    public void testDeleteUserById() throws Exception {

        String id = "1234";
        when(storageService.deleteUserdata(id)).thenCallRealMethod();

        mockMvc.perform(delete("/user/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User data successfully deleted!"));
    }
}
