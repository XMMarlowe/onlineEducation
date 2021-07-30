package com.marlowe.staservice.service;

import com.marlowe.staservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

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

    /**
     * 图表显示，返回两部分数据，日期json数组，数量json数组
     *
     * @param type
     * @param begin
     * @param end
     * @return
     */
    Map<String, Object> getShowData(String type, String begin, String end);
}
