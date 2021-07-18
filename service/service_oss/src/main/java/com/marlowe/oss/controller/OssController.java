package com.marlowe.oss.controller;

import com.marlowe.commonutils.R;
import com.marlowe.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: education_parent
 * @description:
 * @author: Marlowe
 * @create: 2021-07-18 15:18
 **/
@Api(tags = "oss控制器")
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    /**
     * 上传头像到oss
     *
     * @param file
     * @return
     */
    @ApiOperation("上传头像到oss")
    @PostMapping
    public R uploadOssFile(MultipartFile file) {
        // 获取上传的文件 MultipartFile
        String url = ossService.uploadFileAvata(file);
        return R.ok().data("url", url);
    }

}
