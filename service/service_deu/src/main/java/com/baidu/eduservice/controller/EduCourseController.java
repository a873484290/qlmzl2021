package com.baidu.eduservice.controller;


import com.baidu.commonutils.R;
import com.baidu.eduservice.entity.vo.CourseQuery;
import com.baidu.eduservice.service.EduChapterService;
import com.baidu.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author lw
 * @since 2021-02-18
 */
@RestController
@RequestMapping("/eduservice/edu-course")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("add")
    public R saveCourse(CourseQuery courseQuery){
        eduCourseService.saveCourse(courseQuery);

        return R.ok();
    }
}

