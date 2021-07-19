package com.marlowe.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marlowe.eduservice.entity.EduSubject;
import com.marlowe.eduservice.entity.excel.SubjectData;
import com.marlowe.eduservice.entity.subject.OneSubject;
import com.marlowe.eduservice.entity.subject.TwoSubject;
import com.marlowe.eduservice.listener.SubjectExcelListener;
import com.marlowe.eduservice.mapper.EduSubjectMapper;
import com.marlowe.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-19
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    /**
     * 添加课程分类
     *
     * @param file
     * @param subjectService
     */
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            InputStream in = file.getInputStream();
            // 调用方法进行读取
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 课程分类列表(树形)
     *
     * @return
     */
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        // 1.查询所有一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", "0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);
        // 2.查询所有二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperOne.ne("parent_id", "0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        // 创建list集合，用于存储最终封装的数据
        List<OneSubject> finalSubjectList = new ArrayList<>();

        // 3.封装一级分类
        // 查询出来所有的一级分类list集合遍历，得到每个一级分类对象，获取每个一级分类对象值
        // 封装到要求的list集合里面
        for (EduSubject eduSubject : oneSubjectList) {
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduSubject, oneSubject);
            // 将多个OneSubject放到finalSubjectList中
            finalSubjectList.add(oneSubject);

            // 4.封装二级分类
            // 在一级分类循环遍历查询所有的二级分类
            // 创建list集合封装每一个一级分类的二级分类
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            // 遍历二级分类list集合
            for (EduSubject tsubject : twoSubjectList) {
                // 判断二级分类的parentid和一级分类的id是否一样
                if (tsubject.getParentId().equals(eduSubject.getId())) {
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tsubject, twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoFinalSubjectList);
        }
        return finalSubjectList;
    }
}
