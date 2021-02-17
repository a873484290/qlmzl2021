package com.baidu.eduservice.controller;


import com.baidu.commonutils.R;
import com.baidu.eduservice.entity.EduSubject;
import com.baidu.eduservice.entity.OneSubject;
import com.baidu.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author lw
 * @since 2021-02-16
 */
@Api(description = "excel读写")
@RestController
@RequestMapping("/edu/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    //获取上传文件，写入数据库文件
    @PostMapping("read")
    public R addSubject(MultipartFile file){

        eduSubjectService.saveSubject(file,eduSubjectService);
        return R.ok().data("success","写入成功");
    }

    //列表
    @GetMapping("getAllSubject")
    public R getAllSubject(){

       List<OneSubject> list = eduSubjectService.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }


}

