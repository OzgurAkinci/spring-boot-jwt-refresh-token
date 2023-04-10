package com.app.spring.security.jwt.service.fmanager;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileServiceFactory {

    @Qualifier("fileServiceImpl")
    private final FileService nativeFileService;

    public FileService getInstance() {
        return nativeFileService;
    }

    public String getFileSeparator() {
        return FileServiceImpl.PATH_SEPARATOR;
    }

}
