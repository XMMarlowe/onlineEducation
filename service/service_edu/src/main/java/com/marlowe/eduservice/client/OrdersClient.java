package com.marlowe.eduservice.client;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: onlineEducation
 * @description:
 * @author: Marlowe
 * @create: 2021-07-29 20:17
 **/
@Component
@FeignClient("service-order")
public interface OrdersClient {

    /**
     * 根据课程id和用户id查询订单表中订单状态
     *
     * @param courseId
     * @param memberId
     * @return
     */
    @ApiOperation("根据课程id和用户id查询订单表中订单状态")
    @GetMapping("/eduorder/order/isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);
}
