package com.marlowe.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marlowe.eduservice.entity.EduChapter;
import com.marlowe.eduservice.entity.EduVideo;
import com.marlowe.eduservice.entity.chapter.ChapterVo;
import com.marlowe.eduservice.entity.chapter.VideoVo;
import com.marlowe.eduservice.mapper.EduChapterMapper;
import com.marlowe.eduservice.mapper.EduVideoMapper;
import com.marlowe.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marlowe.eduservice.service.EduVideoService;
import com.marlowe.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-20
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {


    @Autowired
    private EduVideoService videoService;

    /**
     * 根据课程id查询课程大纲列表
     *
     * @param courseId
     * @return
     */
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {

        // 1. 根据课程id查询课程里面所有的章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.orderByAsc("sort");
        wrapperChapter.eq("course_id", courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapperChapter);

        // 2. 根据课程id查询课程里面所有的小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.orderByAsc("sort");
        wrapperVideo.eq("course_id", courseId);
        List<EduVideo> eduVideoList = videoService.list(wrapperVideo);

        // 创建list集合，用于最终封装数据
        List<ChapterVo> finalList = new ArrayList<>();

        // 3. 遍历查询章节list集合进行封装
        // 遍历查询章节list集合
        for (EduChapter eduChapter : eduChapterList) {
            // eduChapter对象值复制到ChapterVo里面
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            // 把chapterVo放到最终的集合中
            finalList.add(chapterVo);

            // 创建集合，用于封装章节的小节
            List<VideoVo> videoList = new ArrayList<>();

            // 4.遍历查询小节list集合进行封装
            for (EduVideo eduVideo : eduVideoList) {
                // 判断小节里面chapterId和章节里面id是否一样
                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    // 放到小节封装集合
                    videoList.add(videoVo);
                }
            }

            // 把封装之后的小节list集合，放到章节对象里面
            chapterVo.setChildren(videoList);
        }

        return finalList;
    }

    /**
     * 删除章节的方法
     *
     * @param chapterId
     * @return
     */
    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        int count = videoService.count(wrapper);
        if (count > 0) {
            throw new GuliException(20001, "不能删除");
        } else {
            int result = baseMapper.deleteById(chapterId);
            return result > 0;
        }
    }

    /**
     * 根据课程id删除章节
     *
     * @param courseId
     */
    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        baseMapper.delete(wrapper);
    }
}
