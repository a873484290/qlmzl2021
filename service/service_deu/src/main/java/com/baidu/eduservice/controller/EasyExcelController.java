package com.baidu.eduservice.controller;

import com.alibaba.excel.EasyExcel;
import com.baidu.commonutils.R;
import com.baidu.eduservice.listener.ExcelListener;
import com.baidu.eduservice.entity.excel.ClassData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/edu/excel")
public class EasyExcelController {

    @PostMapping("writeExcel")
    void ExcelWrite(){
        //设置文件夹地址和excel文件名称
        String fileName ="E:\\Spring_2020\\excel.xlsx";

        //使用EasyExcel实现写
        EasyExcel.write(fileName, ClassData.class).sheet().doWrite(getData());

    }

    private static List<ClassData> getData(){
        List<ClassData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ClassData data = new ClassData();
            data.setSno(i);
            data.setName("exc"+i);
            list.add(data);
        }
        return  list;
    }

    //读取excel
    @PostMapping("readExcel")
    R ReadWrite(){
        //设置文件夹地址和excel文件名称
        String fileName ="E:\\Spring_2020\\excel.xlsx";

        //使用EasyExcel实现读取
        EasyExcel.read(fileName, ClassData.class,new ExcelListener()).sheet().doRead();
        return R.ok();
    }

}
