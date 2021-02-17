package com.baidu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baidu.eduservice.entity.excel.ClassData;

import java.util.Map;

//创建监听 读取excel
public class ExcelListener  extends AnalysisEventListener<ClassData> {
    //逐行读取excel内容
    @Override
    public void invoke(ClassData data, AnalysisContext analysisContext) {
        System.out.println("data"+data);
    }

    //读取表头
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头: "+headMap);
    }

    //读取之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
