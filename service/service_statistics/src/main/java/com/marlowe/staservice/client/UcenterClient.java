package com.marlowe.staservice.client;

import com.marlowe.commonutils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: onlineEducation
 * @description:
 * @author: Marlowe
 * @create: 2021-07-29 23:15
 **/
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    /**
     * 查询某一天注册的人数
     *
     * @param day
     * @return
     */
    @ApiOperation("查询某一天注册的人数")
    @GetMapping("/educenter/member/countRegister/{day}")
    public R countRegister(@PathVariable("day") String day);
}
