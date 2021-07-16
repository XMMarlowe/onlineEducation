package com.marlowe.eduservice.controller;


import com.marlowe.eduservice.entity.EduTeacher;
import com.marlowe.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-16
 */
@RestController
@RequestMapping("/eduservice/teacher")
@Api(tags = "讲师管理控制器")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    /**
     * 查询所有讲师
     *
     * @return
     */
    @GetMapping("findAll")
    @ApiOperation("查找所有讲师")
    public List<EduTeacher> findAllTeacher() {
        List<EduTeacher> list = teacherService.list(null);
        return list;
    }

    @DeleteMapping("{id}")
    public boolean removeTeacher(@PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        return flag;
    }

}

