package com.baidu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baidu.eduservice.entity.EduSubject;
import com.baidu.eduservice.entity.excel.SubjectMenu;
import com.baidu.eduservice.service.EduSubjectService;
import com.baidu.servicebase.exceptionhandler.ZdyException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import java.util.Map;


public class SubjectExcelListener  extends AnalysisEventListener<SubjectMenu> {

//    @Autowired
//    EduSubjectService eduSubjectService;

    /**
     * 手动new listener
     */
    public EduSubjectService eduSubjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }


    //subjectData  一级二级菜单
    @Override
    public void invoke(SubjectMenu subjectData, AnalysisContext analysisContext) {
        if(subjectData==null){
            throw new ZdyException(20002,"表格数据为空");
        }

        System.out.println("subjectData:"+subjectData);
        //读取 一级分类不能重复添加
        EduSubject oneEduSubject = this.oneEduSubject(subjectData.getOneSubjectName());
        if(oneEduSubject==null){  //没有相同的一级
            oneEduSubject  = new EduSubject();
            oneEduSubject.setParentId("0");
            oneEduSubject.setTitle(subjectData.getOneSubjectName());
            System.out.println("oneEduSubject:"+oneEduSubject);
            boolean save = eduSubjectService.save(oneEduSubject);

        }

        //二级分类 不能重复添加
        String pid=oneEduSubject.getId();  //一级分类的id值
        EduSubject twoEduSubject = this.twoEduSubject(subjectData.getTwoSubjectName(),pid);
        if(twoEduSubject==null){  //没有相同的一级
            twoEduSubject = new EduSubject();
            twoEduSubject.setParentId(pid);
            twoEduSubject.setTitle(subjectData.getTwoSubjectName());
            eduSubjectService.save(twoEduSubject);

        }

    }

    private EduSubject oneEduSubject(String name){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");  //一级分类
        EduSubject one = eduSubjectService.getOne(wrapper);
        return one;
    }

    private EduSubject twoEduSubject(String name,String pid){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);  //二级分类
        EduSubject two = eduSubjectService.getOne(wrapper);
        return two;
    }


    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头: "+headMap);

    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
