package com.marlowe.staservice.controller;


import com.marlowe.commonutils.R;
import com.marlowe.staservice.client.UcenterClient;
import com.marlowe.staservice.service.StatisticsDailyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-29
 */
@RestController
@RequestMapping("/staservice/sta")
@CrossOrigin
public class StatisticsDailyController {




    @Autowired
    private StatisticsDailyService staService;


    /**
     * 统计某一天注册人数，生成统计数据
     *
     * @param day
     * @return
     */
    @ApiOperation("统计某一天注册人数，生成统计数据")
    @GetMapping("registerCount/{day}")
    public R registerCount(@PathVariable String day) {
        staService.registerCount(day);
        return R.ok();
    }


}

