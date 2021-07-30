package com.marlowe.staservice.schedule;

import com.marlowe.staservice.service.StatisticsDailyService;
import com.marlowe.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @program: onlineEducation
 * @description: 定时任务
 * @author: Marlowe
 * @create: 2021-07-30 07:54
 **/
@Component
public class ScheduledTask {

    @Autowired
    private StatisticsDailyService staservice;

    /**
     * 在每天凌晨一点，把前一天的数据查询添加
     */
    @Scheduled(cron = "0 0 1 * * ? ")
    public void task() {
        staservice.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}
