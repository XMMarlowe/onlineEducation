package com.marlowe.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.marlowe.servicebase.exceptionhandler.GuliException;
import com.marlowe.vod.service.VodService;
import com.marlowe.commonutils.R;
import com.marlowe.vod.utils.ConstantVodUtils;
import com.marlowe.vod.utils.InitVodClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @program: education_parent
 * @description:
 * @author: Marlowe
 * @create: 2021-07-22 23:28
 **/
@Api(tags = "vod控制器")
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    /**
     * 上传视频到阿里云
     *
     * @param file
     * @return
     */
    @ApiOperation("上传视频到阿里云")
    @PostMapping("uploadAlyVideo")
    public R uploadAlyVideo(MultipartFile file) {
        String videoId = vodService.uploadVideoAly(file);
        return R.ok().data("videoId", videoId);
    }

    /**
     * 通过id删除阿里云中的视频
     *
     * @param id
     * @return
     */
    @ApiOperation("通过id删除阿里云中的视频")
    @DeleteMapping("removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable String id) {
        vodService.removeAlyVideoById(id);
        return R.ok();
    }


    /**
     * 删除多个阿里云中的视频
     *
     * @param videoList
     * @return
     */
    @ApiOperation("删除多个阿里云中的视频")
    @DeleteMapping("delete-batch")
    public R deleteBatch(@RequestParam("videoList") List<String> videoList) {
        vodService.removeMoreAlyVideo(videoList);
        return R.ok();
    }
}
