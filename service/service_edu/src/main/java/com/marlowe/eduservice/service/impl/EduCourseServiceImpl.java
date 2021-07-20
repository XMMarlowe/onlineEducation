package com.marlowe.eduservice.service.impl;

import com.marlowe.eduservice.entity.EduCourse;
import com.marlowe.eduservice.entity.EduCourseDescription;
import com.marlowe.eduservice.entity.vo.CourseInfoVo;
import com.marlowe.eduservice.mapper.EduCourseDescriptionMapper;
import com.marlowe.eduservice.mapper.EduCourseMapper;
import com.marlowe.eduservice.service.EduCourseDescriptionService;
import com.marlowe.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marlowe.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-20
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseMapper courseMapper;

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    /**
     * 添加课程基本信息
     *
     * @param courseInfoVo
     * @return
     */
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1. 向课程表添加课程基本信息
        // CourseInfoVo对象转换eduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = courseMapper.insert(eduCourse);
        if (insert <= 0) {
            throw new GuliException(20001, "添加课程失败");
        }

        // 获取添加之后课程id
        String cid = eduCourse.getId();

        //2. 向课程简介表添加课程简介
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        // 设置描述id就是课程id
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);
        return cid;
    }
}
