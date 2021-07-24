package com.marlowe.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marlowe.eduservice.entity.EduTeacher;
import com.marlowe.eduservice.mapper.EduTeacherMapper;
import com.marlowe.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-16
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    /**
     * 查询主页显示的名师
     *
     * @return
     */
    @Cacheable(value = "teachers", key = "'selectTeacherList'")
    @Override
    public List<EduTeacher> findTeachers() {
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("gmt_create");
        wrapperTeacher.last("limit 4");
        List<EduTeacher> list = baseMapper.selectList(wrapperTeacher);
        return list;
    }
}
