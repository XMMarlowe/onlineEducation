package com.marlowe.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: education_parent
 * @description: 章节Vo
 * @author: Marlowe
 * @create: 2021-07-20 23:34
 **/
@Data
public class ChapterVo {

    private String id;

    private String title;

    /**
     * 小节
     */
    private List<VideoVo> children = new ArrayList<>();
}
