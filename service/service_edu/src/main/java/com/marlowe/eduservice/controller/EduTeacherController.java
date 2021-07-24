package com.marlowe.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.marlowe.commonutils.R;
import com.marlowe.eduservice.entity.EduTeacher;
import com.marlowe.eduservice.entity.vo.TeacherQuery;
import com.marlowe.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
@CrossOrigin
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

        // 创建一个page对象
        Page<EduTeacher> pageTeacher = new Page<>(pageNo, pageSize);
        teacherService.page(pageTeacher, null);

        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
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
    @PostMapping("pageTeacherCondition/{pageNo}/{pageSize}")
    public R pageTeacherCondition(@PathVariable long pageNo, @PathVariable long pageSize, @RequestBody(required = false) TeacherQuery teacherQuery) {
        // 创建一个page对线
        Page<EduTeacher> pageTeacher = new Page<>(pageNo, pageSize);

        // 构造条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        // 多条件组合查询
        String name = teacherQuery.getName();
        String career = teacherQuery.getCareer();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(career)) {
            wrapper.eq("career", career);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }

        teacherService.page(pageTeacher, wrapper);

        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total", total).data("rows", records);
    }

    /**
     * 添加讲师
     *
     * @param eduTeacher
     * @return
     */
    @ApiOperation("添加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = teacherService.save(eduTeacher);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 根据id查询讲师
     *
     * @param id
     * @return
     */
    @ApiOperation("根据id查询讲师")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id) {
        EduTeacher eduTeacher = teacherService.getById(id);
        return R.ok().data("teacher", eduTeacher);
    }

    /**
     * 更新讲师信息
     *
     * @param eduTeacher
     * @return
     */
    @ApiOperation("更新讲师信息")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = teacherService.updateById(eduTeacher);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 查询所有讲师职位
     *
     * @return
     */
    @ApiOperation("查询所有讲师职位")
    @GetMapping("allCareers")
    public R findAllCareer() {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper();
        wrapper.select("distinct career");
        List<EduTeacher> careers = teacherService.list(wrapper);
        int total = careers.size();
        return R.ok().data("total",total).data("careers", careers);
    }


}

