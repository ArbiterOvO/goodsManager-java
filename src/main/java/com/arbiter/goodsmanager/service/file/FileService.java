package com.arbiter.goodsmanager.service.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    void upload(MultipartFile file, String fileName) throws IOException;
}
