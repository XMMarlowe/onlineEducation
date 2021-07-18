package com.marlowe.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @program: education_parent
 * @description:
 * @author: Marlowe
 * @create: 2021-07-18 15:17
 **/
public interface OssService {
    /**
     * 上传头像
     *
     * @param file
     * @return
     */
    String uploadFileAvata(MultipartFile file);
}
