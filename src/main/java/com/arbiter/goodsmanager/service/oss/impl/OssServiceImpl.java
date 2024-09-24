package com.arbiter.goodsmanager.service.oss.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.arbiter.goodsmanager.service.oss.OssService;
import com.arbiter.goodsmanager.util.ConstantPropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.OutputStream;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.KEY_SECRET;
        // 填写Bucket名称
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        //创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {

            // 获取上传文件输入流
            InputStream inputStream = file.getInputStream();

            //获取文件名称
            String fileName = file.getOriginalFilename();

            // meta设置请求头
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType("image/jpg");

            //调用oss方法实现上传
            //第一个参数 Bucket名称
            //第二个参数 上传到oss文件路径或文件名称
            //第三个参数 上传文件输入流
            //第四个参数 请求头
            ossClient.putObject(bucketName, fileName, inputStream,meta);

            // 返回上传到阿里OSS的路径
            String url = "https://".concat(bucketName).concat(".").concat(endpoint).concat("/").concat(fileName);

            return url;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    @Override
    public OutputStream download(String url) {

        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.KEY_SECRET;
        // 填写Bucket名称
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        //创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        OSSObject object = ossClient.getObject(bucketName, url);
        object.getObjectContent();


        return null;
    }
}
