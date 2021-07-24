package com.marlowe.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marlowe.eduservice.entity.EduSubject;
import com.marlowe.eduservice.entity.excel.SubjectData;
import com.marlowe.eduservice.service.EduSubjectService;
import com.marlowe.servicebase.exceptionhandler.GuliException;

/**
 * @program: education_parent
 * @description:
 * @author: Marlowe
 * @create: 2021-07-19 22:52
 **/
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    // 因为SubjectExcelListener不能交给Spring容器经常管理，需要自己new，不能注入其他对象
    // 不能实现数据库操作

    public EduSubjectService eduSubjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    /**
     * 按行读取数据
     *
     * @param subjectData
     * @param analysisContext
     */
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new GuliException(200001, "文件数据为空");
        }

        // 一行一行读取，每次读取有两个值，第一个值-一级分类，第二个值-二级分类
        // 判断一级分类是否重复
        EduSubject existOneSubject = this.existOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        if (existOneSubject == null) {
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(existOneSubject);
        }

        // 获取一级分类id值
        String pid = existOneSubject.getId();

        // 添加二级分类
        // 判断二级分类是否重复

        EduSubject existTwoSubject = this.existTwoSubject(eduSubjectService, subjectData.getTwoSubjectName(), pid);
        if (existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            eduSubjectService.save(existTwoSubject);
        }

    }


    /**
     * 判断一级分类不能重复添加
     *
     * @param eduSubjectService
     * @param name
     * @return
     */
    private EduSubject existOneSubject(EduSubjectService eduSubjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", 0);
        EduSubject oneSubject = eduSubjectService.getOne(wrapper);
        return oneSubject;
    }

    /**
     * 判断一级分类不能重复添加
     *
     * @param eduSubjectService
     * @param name
     * @return
     */
    private EduSubject existTwoSubject(EduSubjectService eduSubjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        EduSubject twoSubject = eduSubjectService.getOne(wrapper);
        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
