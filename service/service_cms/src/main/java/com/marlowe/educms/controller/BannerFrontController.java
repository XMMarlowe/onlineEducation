package com.marlowe.educms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marlowe.commonutils.R;
import com.marlowe.educms.entity.CrmBanner;
import com.marlowe.educms.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-24
 */
@Api(tags = "banner前台控制器")
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class BannerFrontController {


    @Autowired
    private CrmBannerService bannerService;


    /**
     * 获取首页所有banner
     *
     * @return
     */
    @ApiOperation(value = "获取首页所有banner")
    @GetMapping("getAllBanner")
    public R getAll() {
        List<CrmBanner> list = bannerService.selectAllBanner();
        return R.ok().data("list", list);
    }

}

