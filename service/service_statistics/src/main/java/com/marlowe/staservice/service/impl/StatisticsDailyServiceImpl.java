package com.marlowe.staservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marlowe.commonutils.R;
import com.marlowe.staservice.client.UcenterClient;
import com.marlowe.staservice.entity.StatisticsDaily;
import com.marlowe.staservice.mapper.StatisticsDailyMapper;
import com.marlowe.staservice.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-29
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private UcenterClient ucenterClient;

    /**
     * 统计某一天注册人数，生成统计数据
     *
     * @param day
     */
    @Override
    public void registerCount(String day) {
        // 添加记录之前，先删除相同日期的数据
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated", day);
        baseMapper.delete(wrapper);

        // 远程调用得到某一天的注册人数
        R r = ucenterClient.countRegister(day);
        Integer countRegister = (Integer) r.getData().get("countRegister");

        // 把获取数据添加到数据库，统计分析表中
        StatisticsDaily sta = new StatisticsDaily();
        sta.setRegisterNum(countRegister);
        sta.setDateCalculated(day);

        // 随机数设置以下值
        sta.setVideoViewNum(RandomUtils.nextInt(100, 200));
        sta.setLoginNum(RandomUtils.nextInt(100, 200));
        sta.setCourseNum(RandomUtils.nextInt(100, 200));

        baseMapper.insert(sta);
    }

    /**
     * 图表显示，返回两部分数据，日期json数组，数量json数组
     *
     * @param type
     * @param begin
     * @param end
     * @return
     */
    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper();
        wrapper.between("date_calculated", begin, end);
        // 根据具体类型查询人数
        wrapper.select("date_calculated", type);
        List<StatisticsDaily> staList = baseMapper.selectList(wrapper);

        // 分别封装日期和时间
        List<String> dateList = new ArrayList<>();
        List<Integer> numList = new ArrayList<>();

        for (StatisticsDaily daily : staList) {
            dateList.add(daily.getDateCalculated());
            // 判断具体封装哪个数据
            switch (type) {
                case "login_num": {

                    numList.add(daily.getLoginNum());
                    break;
                }
                case "register_num": {
                    numList.add(daily.getRegisterNum());
                    break;
                }
                case "video_view_num": {
                    numList.add(daily.getVideoViewNum());
                    break;
                }
                case "course_num": {
                    numList.add(daily.getCourseNum());
                    break;
                }
                default:
                    break;
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("dateList", dateList);
        map.put("numList", numList);
        return map;
    }
}
