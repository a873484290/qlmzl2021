package com.baidu.oss.controller;

import com.baidu.commonutils.R;
import com.baidu.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping ("/oss")
@CrossOrigin
public class ossController {

    @Autowired
    OssService ossService;

    @PostMapping("fileOss")
    public R uploadOssFile(MultipartFile file){   //参数名不是file 会返回null  swagger测试没问题

        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("oss_url",url);
    }
}
