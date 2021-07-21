package com.marlowe.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: education_parent
 * @description: 课程发布信息
 * @author: Marlowe
 * @create: 2021-07-20 08:13
 **/
@ApiModel(value = "课程发布信息")
@Data
public class CoursePublishVo {

    @ApiModelProperty(value = "课程ID")
    private String id;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;

    @ApiModelProperty(value = "一级分类ID")
    private String subjectLevelOne;

    @ApiModelProperty(value = "二级分类ID")
    private String subjectLevelTwo;

    @ApiModelProperty(value = "讲师姓名")
    private String teacherName;

    @ApiModelProperty(value = "销售数量")
    private String price;
}
