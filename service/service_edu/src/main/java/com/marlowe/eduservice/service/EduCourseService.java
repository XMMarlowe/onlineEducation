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
     * @return
     */
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * 根据课程id查询课程基本信息
     *
     * @param courseId
     * @return
     */
    CourseInfoVo getCourseInfo(String courseId);

    /**
     * 修改课程信息
     *
     * @param courseInfoVo
     */
    void updateCourseInfo(CourseInfoVo courseInfoVo);
}
