package com.marlowe.eduorder.client;

import com.marlowe.commonutils.ordervo.UcenterMemberOrder;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @program: education_parent
 * @description:
 * @author: Marlowe
 * @create: 2021-07-28 21:22
 **/
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    /**
     * 根据用户id获取用户信息
     *
     * @param id
     * @return
     */
    @ApiOperation("根据用户id获取用户信息")
    @PostMapping("/educenter/member/getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable("id") String id);
}
