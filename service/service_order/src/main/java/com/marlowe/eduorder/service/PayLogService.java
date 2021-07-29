package com.marlowe.eduorder.service;

import com.marlowe.eduorder.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-29
 */
public interface PayLogService extends IService<PayLog> {

    /**
     * 根据订单号生成微信支付二维码
     *
     * @param orderNo
     * @return
     */
    Map createNative(String orderNo);

    /**
     * 根据订单号查询订单支付状态
     *
     * @param orderNo
     * @return
     */
    Map<String, String> queryPayStatus(String orderNo);

    /**
     * 向支付表中添加记录，并更新订单状态
     *
     * @param map
     */
    void updateOrdersStatus(Map<String, String> map);

}
