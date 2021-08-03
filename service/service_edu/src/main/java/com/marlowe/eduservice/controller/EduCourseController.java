package com.marlowe.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.marlowe.commonutils.R;
import com.marlowe.eduservice.entity.EduCourse;
import com.marlowe.eduservice.entity.vo.CourseInfoVo;
import com.marlowe.eduservice.entity.vo.CoursePublishVo;
import com.marlowe.eduservice.entity.vo.CourseQuery;
import com.marlowe.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-20
 */
@Api(tags = "课程管理控制器")
@RestController
@RequestMapping("/eduservice/course")
//@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;


    /**
     * 添加课程基本信息
     *
     * @param courseInfoVo
     * @return
     */
    @ApiOperation("添加课程基本信息")
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("id", id);
    }

    /**
     * 根据课程id查询课程基本信息
     *
     * @param courseId
     * @return
     */
    @ApiOperation("根据课程id查询课程基本信息")
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo", courseInfoVo);
    }

    /**
     * 修改课程信息
     *
     * @param courseInfoVo
     * @return
     */
    @ApiOperation("修改课程信息")
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    /**
     * 获得课程发布信息
     *
     * @param id
     * @return
     */
    @ApiOperation("获得课程发布信息")
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = courseService.publicCourseInfo(id);
        return R.ok().data("publishCourse", coursePublishVo);
    }

    /**
     * 发布课程，改变status值： Draft-->Normal
     *
     * @param id
     * @return
     */
    @ApiOperation("发布课程，改变status值： Draft-->Normal")
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        courseService.updateById(eduCourse);
        return R.ok();
    }

    /**
     * 取消发布课程，改变status值： Normal-->Draft
     *
     * @param id
     * @return
     */
    @ApiOperation("发布课程，改变status值： Normal-->Draft")
    @PostMapping("unpublishCourse/{id}")
    public R unpublishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Draft");
        courseService.updateById(eduCourse);
        return R.ok();
    }

    /**
     * 分页查询课程
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation("分页查询课程")
    @GetMapping("pageCourse/{pageNo}/{pageSize}")
    public R pageListCourse(@PathVariable long pageNo, @PathVariable long pageSize) {

        // 创建一个page对象
        Page<EduCourse> pageCourse = new Page<>(pageNo, pageSize);
        courseService.page(pageCourse, null);

        long total = pageCourse.getTotal();
        List<EduCourse> records = pageCourse.getRecords();

        return R.ok().data("total", total).data("rows", records);
    }

    /**
     * 带条件的分页查询
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation("带条件的分页查询")
    @PostMapping("pageCourseCondition/{pageNo}/{pageSize}")
    public R pageCourseCondition(@PathVariable long pageNo, @PathVariable long pageSize, @RequestBody(required = false) CourseQuery courseQuery) {
        // 创建一个page对象
        Page<EduCourse> pageCourse = new Page<>(pageNo, pageSize);

        // 构造条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

        // 多条件组合查询
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        String begin = courseQuery.getBegin();
        String end = courseQuery.getEnd();

        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq("status", status);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }

        courseService.page(pageCourse, wrapper);

        long total = pageCourse.getTotal();
        List<EduCourse> records = pageCourse.getRecords();
        return R.ok().data("total", total).data("rows", records);
    }


    /**
     * 删除课程
     *
     * @param courseId
     * @return
     */
    @ApiOperation("删除课程")
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId) {
        courseService.removeCourse(courseId);
        return R.ok();
    }
}

