package com.marlowe.eduservice.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.marlowe.commonutils.R;
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
    @ApiOperation("查找所有讲师")
    @GetMapping("findAll")
    public R findAllTeacher() {
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items", list);
    }

    /**
     * 根据id逻辑删除讲师
     *
     * @param id
     * @return
     */
    @ApiOperation("根据id逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }


    /**
     * 分页查询讲师
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation("分页查询讲师")
    @GetMapping("pageTeacher/{pageNo}/{pageSize}")
    public R pageListTeacher(@PathVariable long pageNo, @PathVariable long pageSize) {

        // 创建一个page对线
        Page<EduTeacher> pageTeacher = new Page<>(pageNo, pageSize);
        teacherService.page(pageTeacher, null);

        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total", total).data("rows", records);
    }

}

