package com.marlowe.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: education_parent
 * @description: 一级分类
 * @author: Marlowe
 * @create: 2021-07-20 06:25
 **/
@Data
public class OneSubject {
    private String id;

    private String title;

    // 一个一级分类有多个二级分类
    private List<TwoSubject> children = new ArrayList<>();
}
