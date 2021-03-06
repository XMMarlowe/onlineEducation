package com.marlowe.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marlowe.eduservice.entity.EduCourse;
import com.marlowe.eduservice.entity.EduCourseDescription;
import com.marlowe.eduservice.entity.vo.CourseInfoVo;
import com.marlowe.eduservice.entity.vo.CoursePublishVo;
import com.marlowe.eduservice.entity.vo.CourseWebVo;
import com.marlowe.eduservice.mapper.EduCourseMapper;
import com.marlowe.eduservice.service.EduChapterService;
import com.marlowe.eduservice.service.EduCourseDescriptionService;
import com.marlowe.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marlowe.eduservice.service.EduVideoService;
import com.marlowe.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private EduCourseDescriptionService courseDescriptionService;


    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService eduChapterService;

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
        int insert = baseMapper.insert(eduCourse);
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

    /**
     * 根据课程id查询课程基本信息
     *
     * @param courseId
     * @return
     */
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        // 1. 查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);

        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);
        // 2. 查询描述表
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }

    /**
     * 修改课程信息
     *
     * @param courseInfoVo
     */
    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        // 1. 修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if (update == 0) {
            throw new GuliException(20001, "修改课程信息失败");
        }

        // 2. 修改描述表
        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(description);
    }

    /**
     * 获得课程发布信息
     *
     * @param id
     * @return
     */
    @Override
    public CoursePublishVo publicCourseInfo(String id) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    /**
     * 删除课程
     *
     * @param courseId
     */
    @Override
    public void removeCourse(String courseId) {
        // 1. 根据课程id删除小节
        eduVideoService.removeVideoByCourseId(courseId);

        // 2. 根据课程id删除章节
        eduChapterService.removeChapterByCourseId(courseId);

        // 3. 根据课程id删除描述
        courseDescriptionService.removeById(courseId);

        // 4. 根据课程id删除课程本身
        int result = baseMapper.deleteById(courseId);

        if (result == 0) {
            throw new GuliException(20001, "删除失败");
        }
    }

    /**
     * 查询主页显示的课程
     *
     * @return
     */
    @Cacheable(value = "courses", key = "'selectCourseList'")
    @Override
    public List<EduCourse> findCourses() {
        QueryWrapper<EduCourse> wrapperCourse = new QueryWrapper<>();
        wrapperCourse.orderByDesc("gmt_create");
        wrapperCourse.last("limit 8");
        List<EduCourse> list = baseMapper.selectList(wrapperCourse);
        return list;
    }

    /**
     * 根据课程id，查询课程基本信息
     *
     * @param courseId
     * @return
     */
    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }


}
