package com.arbiter.goodsmanager.controller.oss;

import com.alibaba.fastjson.JSONObject;
import com.arbiter.goodsmanager.result.Result;
import com.arbiter.goodsmanager.service.oss.OssService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@RequestMapping("/oss/file")
public class OssController {
    final OssService ossService;

    @PostMapping("/upload")
    public Result<String> upload(@RequestBody MultipartFile file) {
        //返回OSS url
        String url = ossService.uploadFileAvatar(file);
        return Result.success(url);
    }

    @PostMapping("/download")
    public Result<String> download(@RequestBody String url) {
        ossService.download(url);
    }
}
