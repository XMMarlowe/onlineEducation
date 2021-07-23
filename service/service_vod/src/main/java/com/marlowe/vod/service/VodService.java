package com.marlowe.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @program: education_parent
 * @description:
 * @author: Marlowe
 * @create: 2021-07-22 23:29
 **/
public interface VodService {
    /**
     * 上传视频到阿里云
     *
     * @param file
     * @return
     */
    String uploadVideoAly(MultipartFile file);

    /**
     * 通过id删除阿里云中的视频
     *
     * @param id
     */
    void removeAlyVideoById(String id);

    /**
     * 删除多个阿里云中的视频
     *
     * @param videoList
     */
    void removeMoreAlyVideo(List videoList);


}
