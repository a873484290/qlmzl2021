package com.baidu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baidu.eduservice.entity.EduSubject;
import com.baidu.eduservice.entity.OneSubject;
import com.baidu.eduservice.entity.TwoSubject;
import com.baidu.eduservice.entity.excel.SubjectMenu;
import com.baidu.eduservice.listener.SubjectExcelListener;
import com.baidu.eduservice.mapper.EduSubjectMapper;
import com.baidu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author lw
 * @since 2021-02-16
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try{
            InputStream inputStream = file.getInputStream();
            //对应实体类
            EasyExcel.read(
                    inputStream, SubjectMenu.class,new SubjectExcelListener(eduSubjectService))
                    .sheet().doRead();
        }catch (Exception e){
        }

    }

    //返回一二级分类
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //查询所有一级分类  parentId=0
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id","0");
        List<EduSubject> subjectOne = baseMapper.selectList(wrapper);
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");//不等于0
        List<EduSubject> subjectTwo = baseMapper.selectList(wrapperTwo);

        //一二级菜单
       List<OneSubject> finalList = new ArrayList<>();
       for(int i =0 ; i<subjectOne.size(); i++){
           EduSubject eduSubject = subjectOne.get(i);
           OneSubject oneSubject = new OneSubject();
           oneSubject.setId(eduSubject.getId());
           oneSubject.setTitle(eduSubject.getTitle()); //BeanUtils.copyProperties(eduSubject,oneSubject);
           finalList.add(oneSubject);

           //  //封装二级数组， 放到一级的children下
           List<TwoSubject> twoList = new ArrayList<>();
           for(int k =0 ; k<subjectTwo.size(); k++){
               EduSubject two = subjectTwo.get(k); //二级菜单内容

               TwoSubject twoSub = new TwoSubject();
               //判断 二级parent_id = 一级id
               if(subjectOne.get(i).getId().equals(subjectTwo.get(k).getParentId())){
                   //二级菜单内容 封装到二级目录
                   BeanUtils.copyProperties(two,twoSub);
                   twoList.add(twoSub);
               }
           }
           oneSubject.setChildren(twoList);
       }
        return finalList;
    }
}
