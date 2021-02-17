package com.baidu.eduservice.controller;


import com.baidu.commonutils.R;
import com.baidu.eduservice.entity.EduTeacher;
import com.baidu.eduservice.entity.vo.TeacherQuery;
import com.baidu.eduservice.service.EduTeacherService;
import com.baidu.servicebase.exceptionhandler.ZdyException;
import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-30
 */
//@Api(description = "CURD")
@RestController
@RequestMapping("/edu/teacher")
@Slf4j
@CrossOrigin //跨域
public class EduTeacherController {

    @Autowired
    EduTeacherService eduTeacherService;

    //rest风格
    @ApiOperation("查询所有")
    @GetMapping("findAll")
    public R findAll() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok()
                .data("item", list);
    }


    @ApiOperation("删除操作")
    @DeleteMapping("{ids}")
    public R delAll(@PathVariable List ids) {
        boolean b = eduTeacherService.removeByIds(ids);
        // eduTeacherService.removeById(ids);
        b = false;
        System.out.println(b);
        if (b) {
            return R.ok()
                    .data("item", b);
        } else {
            return R.error();
        }
    }


    //rest风格
    @ApiOperation("操作222")
    @GetMapping("pageTeacherDel/{current}/{size}")
    public R pageListTeacher(@PathVariable Long current,
                             @PathVariable Long size) {

        //创建page对象
        Page<EduTeacher> page = new Page<>(current, size);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper();
        wrapper.orderByAsc();
        eduTeacherService.page(page, wrapper);
        long size1 = page.getSize();
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();
        LinkedHashMap map = new LinkedHashMap();
        map.put("total:", total);
        map.put("size:", size1);
        map.put("records:", records);

        return R.ok().data(map);

    }

    @ApiOperation("条件查询操作分页")
    @PostMapping("pageTeacherQuery/{current}/{size}")
    public R pageListTeacherQuery(@PathVariable Long current,
                                  @PathVariable Long size
//            ,  @RequestBody(required = false) TeacherQuery teacherQuery
    ) {

        //创建page对象
        Page<EduTeacher> page = new Page<>(current, size);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper();
        wrapper.orderByAsc();

        //动态sql
        //查询拼接条件  name  level  begin  end 数据库表中字段名
//        String name = teacherQuery.getName();
//        Integer level = teacherQuery.getLevel();
//        String begin = teacherQuery.getBegin();
//        String end = teacherQuery.getEnd();
//        if (!StringUtils.isEmpty(name)) {
//            wrapper.like("name", name);
//        }
//        if (!StringUtils.isEmpty(level)) {
//            wrapper.eq("level", level);
//        }
//        if (!StringUtils.isEmpty(begin)) {
//            wrapper.ge("begin", begin);  //ge  大于等于  gt 大于
//        }
//        if (!StringUtils.isEmpty(end)) {
//            wrapper.le("end", end);    //le 小于等于   lt 小于
//        }

        eduTeacherService.page(page, wrapper);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();
        LinkedHashMap<String, Object> map2 = new LinkedHashMap();

        map2.put("total:", total);
        map2.put("records:", records);
        return R.ok().data(map2);

    }


    //4 条件查询带分页的方法
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit
            ,
                                  @RequestBody(required = false) TeacherQuery teacherQuery
    ) {
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);

        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
//        判断条件值是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询分页
        eduTeacherService.page(pageTeacher, wrapper);

        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords(); //数据list集合
        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation("添加操作222")
    @PostMapping("addTeacherQuery")
    public R addTeacherQuery(@RequestBody(required = false) EduTeacher eduTeacher) {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper();
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation("查询操作222")
    @PostMapping("selectTeacher/{id}")
    public R selectTeacherById(@PathVariable String id) {
        EduTeacher byId = eduTeacherService.getById(id);
        return R.ok().data("getById", byId);
    }


    @ApiOperation("修改操作222")
    @PostMapping("updateTeacher/{id}")
    public R updateTeacherById(@RequestBody EduTeacher eduTeacher) {

//        try {
//            int j = 10 / 1;
//        } catch (Exception e) {
//
//            log.error("getMessage()=====" + e.getMessage());//  原来的异常
//            throw new ZdyException(20010, "自定义异常");
//
//        }

        boolean b = eduTeacherService.updateById(eduTeacher);
        return b == true ? R.ok().data("修改结果", b)
                : R.error().data("修改结果", b);

    }

    @ApiOperation("删除操作222")
    @DeleteMapping("removeTeacherById/{id}")
    public R removeTeacherById(@PathVariable String id) {
        boolean b = eduTeacherService.removeById(id);
        return (b == true) ? (R.ok().data("删除结果", b))
                : (R.error().data("删除结果", b));
    }
}

