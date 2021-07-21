package com.marlowe.eduservice.controller;


import com.marlowe.commonutils.R;
import com.marlowe.eduservice.entity.EduVideo;
import com.marlowe.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 根据id删除课程小节
     * TODO:后续完善，删除小节的时候，同时把里面的视频删掉
     *
     * @param id
     * @return
     */
    @ApiOperation("根据id删除课程小节")
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
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

