package com.marlowe.eduservice.mapper;

import com.marlowe.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.marlowe.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-20
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    /**
     * 获得课程发布信息
     *
     * @param courseId
     * @return
     */
    public CoursePublishVo getPublishCourseInfo(String courseId);
}
