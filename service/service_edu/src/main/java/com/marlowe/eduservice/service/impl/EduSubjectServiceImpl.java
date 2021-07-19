package com.marlowe.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.marlowe.eduservice.entity.EduSubject;
import com.marlowe.eduservice.entity.excel.SubjectData;
import com.marlowe.eduservice.listener.SubjectExcelListener;
import com.marlowe.eduservice.mapper.EduSubjectMapper;
import com.marlowe.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

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
}
