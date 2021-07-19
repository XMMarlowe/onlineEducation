package com.marlowe.eduservice.controller;


import com.marlowe.commonutils.R;
import com.marlowe.eduservice.entity.subject.OneSubject;
import com.marlowe.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-19
 */
@Api(tags = "课程分类管理控制器")
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;


    /**
     * 添加课程分类
     *
     * @param file
     * @return
     */
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
        // 上传过来的excel文件
        subjectService.saveSubject(file, subjectService);
        return R.ok();
    }

    /**
     * 课程分类列表（树形）
     *
     * @return
     */
    @ApiOperation("课程分类列表（树形）")
    @GetMapping("getAllSubject")
    public R getAllSubject() {
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return R.ok().data("list", list);
    }
}

