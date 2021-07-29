package com.marlowe.eduorder.service;

import com.marlowe.eduorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-29
 */
public interface OrderService extends IService<Order> {
    /**
     * 根据课程id生成订单
     *
     * @param courseId
     * @param memberId
     * @return
     */
    String createOrders(String courseId, String memberId);


}
