package com.marlowe.eduservice.service;

import com.marlowe.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.marlowe.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-20
 */
public interface EduChapterService extends IService<EduChapter> {

    /**
     * 根据课程id查询课程大纲列表
     *
     * @param courseId
     * @return
     */
    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    /**
     * 删除章节的方法
     *
     * @param chapterId
     * @return
     */
    boolean deleteChapter(String chapterId);

    /**
     * 根据课程id删除章节
     *
     * @param courseId
     */
    void removeChapterByCourseId(String courseId);

}
