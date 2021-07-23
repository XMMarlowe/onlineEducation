package com.marlowe.eduservice.controller;


import com.marlowe.commonutils.R;
import com.marlowe.eduservice.client.VodClient;
import com.marlowe.eduservice.entity.EduVideo;
import com.marlowe.eduservice.service.EduVideoService;
import com.marlowe.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-20
 */
@Api(tags = "课程小节管理控制器")
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    @Autowired
    private VodClient vodClient;

    /**
     * 添加课程小节
     *
     * @param eduVideo
     * @return
     */
    @ApiOperation("添加课程小节")
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return R.ok();
    }

    /**
     * 根据课程小节id删除课程小节
     *
     * @param id
     * @return
     */
    @ApiOperation("根据课程小节id删除课程小节")
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
        // 根据小节id得到视频id
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();

        // 判断小节里面是否有视频id
        if (!StringUtils.isEmpty(videoSourceId)) {
            // 根据视频id，远程调用实现视频删除
            R result = vodClient.removeAlyVideo(videoSourceId);
            if (result.getCode() == 20001) {
                throw new GuliException(20001, "删除视频失败，熔断器...");
            }

        }
        // 删除视频小节
        videoService.removeById(id);
        return R.ok();
    }


    /**
     * 更新课程小节
     *
     * @param eduVideo
     * @return
     */
    @ApiOperation("更新课程小节")
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo) {
        videoService.updateById(eduVideo);
        return R.ok();
    }

}

