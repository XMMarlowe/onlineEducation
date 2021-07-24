package com.marlowe.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: education_parent
 * @description: 课程查询对象封装类
 * @author: Marlowe
 * @create: 2021-07-21 23:37
 **/
@ApiModel(value = "Course查询对象", description = "课程查询对象封装")
@Data
public class CourseQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程名称,模糊查询")
    private String title;

    @ApiModelProperty(value = "状态 Draft：未发布   Normal：已发布")
    private String status;

    /**
     * 注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
     */
    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;


}
