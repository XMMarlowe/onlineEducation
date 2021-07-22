package com.marlowe.eduservice.service;

import com.marlowe.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-20
 */
public interface EduVideoService extends IService<EduVideo> {

    /**
     * 根据课程id删除小节
     * @param courseId
     */
    void removeVideoByCourseId(String courseId);

}
