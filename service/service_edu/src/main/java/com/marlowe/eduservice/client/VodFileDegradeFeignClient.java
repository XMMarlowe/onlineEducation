package com.marlowe.eduservice.client;

import com.marlowe.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: onlineEducation
 * @description: 远程调用出错后执行类
 * @author: Marlowe
 * @create: 2021-07-23 20:11
 **/
@Component
public class VodFileDegradeFeignClient implements VodClient {

    @Override
    public R removeAlyVideo(String id) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R deleteBatch(List<String> videoList) {
        return R.error().message("删除多个视频出错了");
    }
}
