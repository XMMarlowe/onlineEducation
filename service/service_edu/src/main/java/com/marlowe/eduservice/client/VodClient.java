package com.marlowe.eduservice.client;

import com.marlowe.commonutils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program: onlineEducation
 * @description:
 * @author: Marlowe
 * @create: 2021-07-23 14:17
 **/
@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {

    /**
     * 通过id删除阿里云中的视频
     * PathVariable注解一定要指定参数名称，否则报错
     *
     * @param id
     * @return
     */
    @ApiOperation("通过id删除阿里云中的视频")
    @DeleteMapping("/eduvod/video/removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable("id") String id);

    /**
     * 删除多个阿里云中的视频
     *
     * @param videoList
     * @return
     */
    @ApiOperation("删除多个阿里云中的视频")
    @DeleteMapping("/eduvod/video/delete-batch")
    public R deleteBatch(@RequestParam("videoList") List<String> videoList);
}
