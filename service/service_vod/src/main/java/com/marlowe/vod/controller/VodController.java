package com.marlowe.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.*;
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

import java.util.ArrayList;
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

    /**
     * 根据视频id获取视频url
     *
     * @param id
     * @return
     */
    @ApiOperation("根据视频id获取视频url")
    @GetMapping("getPlayUrl/{id}")
    public R getPlayAuth(@PathVariable String id) {

        try {
            // 创建初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            // 创建获取在线url的request和response对象
            GetPlayInfoRequest request = new GetPlayInfoRequest();
            // 向request设置视频id
            request.setVideoId(id);
            // 调用方法得到url
            GetPlayInfoResponse response = client.getAcsResponse(request);
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            List<String> playUrl = new ArrayList<>();
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                playUrl.add(playInfo.getPlayURL());
            }
            return R.ok().data("playUrl", playUrl);
        } catch (Exception e) {
            throw new GuliException(20001, "获取视频Url失败");
        }
    }

}
