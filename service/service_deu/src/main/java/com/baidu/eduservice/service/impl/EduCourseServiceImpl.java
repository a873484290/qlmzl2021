package com.baidu.eduservice.service.impl;

import com.baidu.eduservice.entity.EduCourse;
import com.baidu.eduservice.entity.EduCourseDescription;
import com.baidu.eduservice.entity.vo.CourseQuery;
import com.baidu.eduservice.mapper.EduCourseMapper;
import com.baidu.eduservice.service.EduCourseDescriptionService;
import com.baidu.eduservice.service.EduCourseService;
import com.baidu.servicebase.exceptionhandler.ZdyException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author lw
 * @since 2021-02-18
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    EduCourseDescriptionService courseDescriptionService;

    @Override
    public void saveCourse(CourseQuery courseQuery) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseQuery,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert==0){
             throw new ZdyException(20003,"添加失败");
        }

        EduCourseDescription description = new EduCourseDescription();
        description.setDescription(courseQuery.getDescription());//简介表的课程详情
        //添加描述信息
        courseDescriptionService.save(description);
    }
}
