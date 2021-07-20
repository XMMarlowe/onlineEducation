package com.marlowe.eduservice.service;

import com.marlowe.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.marlowe.eduservice.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-20
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 添加课程基本信息
     *
     * @param courseInfoVo
     */
    void saveCourseInfo(CourseInfoVo courseInfoVo);
}
