package com.marlowe.eduservice.entity.chapter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: education_parent
 * @description: 小节Vo
 * @author: Marlowe
 * @create: 2021-07-20 23:34
 **/
@Data
public class VideoVo {

    @ApiModelProperty(value = "视频ID")
    private String id;

    @ApiModelProperty(value = "视频名称")
    private String title;

    @ApiModelProperty(value = "是否可以试听：0收费 1免费")
    private Boolean isFree;

    @ApiModelProperty(value = "云端视频资源")
    private String videoSourceId;

    @ApiModelProperty(value = "原始文件名称")
    private String videoOriginalName;
}
