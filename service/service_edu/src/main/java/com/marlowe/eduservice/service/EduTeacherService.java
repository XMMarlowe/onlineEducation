package com.marlowe.eduservice.service;

import com.marlowe.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-16
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     * 查询主页显示的名师
     * @return
     */
    List<EduTeacher> findTeachers();
}
