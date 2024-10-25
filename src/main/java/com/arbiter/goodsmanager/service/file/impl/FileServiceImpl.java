package com.arbiter.goodsmanager.service.file.impl;

import cn.hutool.core.io.FileUtil;
import com.arbiter.goodsmanager.configuration.UploadConfig;
import com.arbiter.goodsmanager.service.file.FileService;
import com.arbiter.goodsmanager.util.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public void upload(MultipartFile file, String fileName) throws IOException {
        //创建目录
        FileUtil.mkdir(UploadConfig.path );

        String newPath = UploadConfig.path  + "\\"+fileName;

        FileUtils.write(newPath, file.getInputStream());

    }
}
