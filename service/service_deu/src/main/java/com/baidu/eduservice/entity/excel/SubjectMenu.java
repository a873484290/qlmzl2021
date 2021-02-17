package com.baidu.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SubjectMenu {

    //设置excel表头
    @ExcelProperty(value = "一级菜单",index = 0)
    private String oneSubjectName;

    @ExcelProperty(value = "二级菜单",index = 1)
    private String twoSubjectName;
}
