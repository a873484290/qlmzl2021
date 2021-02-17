package com.baidu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baidu.oss.service.OssService;
import com.baidu.servicebase.exceptionhandler.ZdyException;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import  com.baidu.oss.utils.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

@Service

public class OssServiceImpl implements OssService {

     public String  uploadFileAvatar(MultipartFile f){
     // Endpoint以杭州为例，其它Region请按实际情况填写。
             String endpoint = propertiesUtils.END_POINT;
    // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
             String accessKeyId = propertiesUtils.KEY_ID;
             String accessKeySecret = propertiesUtils.KEY_SECRET;

             String bucketName = propertiesUtils.BUCKET_NAME;
    // 创建OSSClient实例。
             OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);


         String file1="";
    // 上传文件流。
         try{
             InputStream inputStream = f.getInputStream();
             //文件名称
             String datePath = new DateTime().toString("yyyy");//yyyy/MM/dd

             file1= datePath+"/"+UUID.randomUUID().toString().replaceAll("-","")
                     +f.getOriginalFilename();
             ossClient.putObject(bucketName,file1, inputStream);
             //返回路径https://qlmzl.oss-cn-beijing.aliyuncs.com/
             String url = "https://"+bucketName+"."+endpoint+"/"+file1;
             System.out.println("url:"+url);

             // 关闭OSSClient。
             ossClient.shutdown();
             return url;
         }catch (Exception e){
             throw new ZdyException(20010, "文件上传异常");

         }



     }

}
