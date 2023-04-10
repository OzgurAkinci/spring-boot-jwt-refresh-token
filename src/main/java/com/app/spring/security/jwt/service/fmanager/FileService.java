package com.app.spring.security.jwt.service.fmanager;

import com.app.spring.security.jwt.dto.fmanager.AjaxResponse;
import com.app.spring.security.jwt.dto.fmanager.FileView;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;

public interface FileService {
    List<FileView> listDirectories(Optional<String> dir);
    List<FileView> listFiles(Optional<String> dir);
    File getFile(String filePath);
    AjaxResponse upload(MultipartFile file, Optional<String> dir, Optional<String> type);
    AjaxResponse delete(String filePath);
    AjaxResponse addFolder(String folderName, String folderPath);
    AjaxResponse deleteFolder(String folderName, String folderPath);
}
