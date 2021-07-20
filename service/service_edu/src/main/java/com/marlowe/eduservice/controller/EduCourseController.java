package com.marlowe.eduservice.controller;


import com.marlowe.commonutils.R;
import com.marlowe.eduservice.entity.vo.CourseInfoVo;
import com.marlowe.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;


    /**
     * 添加课程基本信息
     *
     * @param courseInfoVo
     * @return
     */
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("id",id);
    }

}

