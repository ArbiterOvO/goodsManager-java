package com.arbiter.goodsmanager.service.oss;

import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;

public interface OssService {
    String uploadFileAvatar(MultipartFile file);

    OutputStream download(String url);
}
