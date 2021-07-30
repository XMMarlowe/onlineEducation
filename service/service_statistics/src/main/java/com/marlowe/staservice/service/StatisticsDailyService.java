package com.marlowe.staservice.service;

import com.marlowe.staservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-29
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    /**
     * 统计某一天注册人数，生成统计数据
     *
     * @param day
     */
    void registerCount(String day);
}
