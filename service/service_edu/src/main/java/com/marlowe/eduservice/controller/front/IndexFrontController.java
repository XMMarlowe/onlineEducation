package com.marlowe.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marlowe.commonutils.R;
import com.marlowe.eduservice.entity.EduCourse;
import com.marlowe.eduservice.entity.EduTeacher;
import com.marlowe.eduservice.service.EduCourseService;
import com.marlowe.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: education_parent
 * @description:
 * @author: Marlowe
 * @create: 2021-07-24 14:15
 **/
@Api(tags = "首页前台控制器")
@RestController
@RequestMapping("/eduservice/indexfront")
//@CrossOrigin
public class IndexFrontController {


    @Autowired
    private EduCourseService courseService;


    @Autowired
    private EduTeacherService teacherService;

    /**
     * 查询8条热门课程，4个名师
     *
     * @return
     */
    @ApiOperation("查询8条热门课程，4个名师")
    @GetMapping("index")
    public R index() {

        // 查询8条热门课程
        List<EduCourse> courseList = courseService.findCourses();

        // 4个名师
        List<EduTeacher> teacherList = teacherService.findTeachers();

        return R.ok().data("courseList", courseList).data("teacherList", teacherList);
    }
}
