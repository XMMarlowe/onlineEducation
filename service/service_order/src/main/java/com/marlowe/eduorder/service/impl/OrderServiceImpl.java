package com.marlowe.eduorder.service.impl;

import com.marlowe.commonutils.ordervo.CourseWebVoOrder;
import com.marlowe.commonutils.ordervo.UcenterMemberOrder;
import com.marlowe.eduorder.client.EduClient;
import com.marlowe.eduorder.client.UcenterClient;
import com.marlowe.eduorder.entity.Order;
import com.marlowe.eduorder.mapper.OrderMapper;
import com.marlowe.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marlowe.eduorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-29
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    /**
     * 根据课程id生成订单
     *
     * @param courseId
     * @param memberId
     * @return
     */
    @Override
    public String createOrders(String courseId, String memberId) {
        // 通过远程调用，根据用户id获取用户信息
        UcenterMemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(memberId);

        // 通过远程调用，根据课程id获取课程信息
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);

        // 创建order对象，向order对象中设置需要的数据
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName("test");
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);

        return order.getOrderNo();
    }
}
