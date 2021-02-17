package com.baidu.eduservice.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OneSubject {

    //一级分类
    private String id;
    private String title ;

    //一级分类下有多个二级分类
    private List<TwoSubject> children = new ArrayList<>();

}
