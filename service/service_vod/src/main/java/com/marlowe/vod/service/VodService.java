package com.marlowe.vod.service;

import org.springframework.web.multipart.MultipartFile;

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
}
