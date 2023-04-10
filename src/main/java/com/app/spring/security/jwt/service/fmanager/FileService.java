package com.app.spring.security.jwt.service.fmanager;

import com.app.spring.security.jwt.dto.fmanager.AjaxResponse;
import com.app.spring.security.jwt.dto.fmanager.FileView;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;

public interface FileService {
    String STORAGE_MODE_NATIVE = "native";
    String STORAGE_MODE_CLOUD = "cloud";

    public List<FileView> listDirectories(Optional<String> dir);

    public List<FileView> listFiles(Optional<String> dir);

    public File getFile(String filePath);

    public AjaxResponse upload(MultipartFile file, Optional<String> dir, Optional<String> type);

    public AjaxResponse delete(String filePath);

    public AjaxResponse addFolder(String folderName, String folderPath);

    public AjaxResponse deleteFolder(String folderName, String folderPath);
}
