package com.marlowe.educms.controller;


import com.marlowe.commonutils.R;
import com.marlowe.educms.entity.CrmBanner;
import com.marlowe.educms.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-24
 */
@Api(tags = "banner后台管理控制器")
@RestController
@RequestMapping("/educms/banneradmin")
@CrossOrigin
public class BannerAdminController {


    @Autowired
    private CrmBannerService bannerService;

    /**
     * 分页查询banner
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation("分页查询banner")
    @GetMapping("pageBanner/{pageNo}/{pageSize}")
    public R pageBanner(@PathVariable long pageNo, @PathVariable long pageSize) {
        Page<CrmBanner> pageBanner = new Page<>(pageNo, pageSize);
        bannerService.page(pageBanner, null);
        return R.ok().data("items", pageBanner.getRecords()).data("total", pageBanner.getTotal());
    }

    /**
     * 添加banner
     *
     * @param crmBanner
     * @return
     */
    @ApiOperation("添加banner")
    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner) {
        bannerService.save(crmBanner);
        return R.ok();
    }


    /**
     * 获取Banner
     *
     * @param id
     * @return
     */
    @ApiOperation("获取Banner")
    @GetMapping("get/{id}")
    public R get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return R.ok().data("item", banner);
    }

    /**
     * 修改banner
     *
     * @param banner
     * @return
     */
    @ApiOperation("修改Banner")
    @PutMapping("update")
    public R updateById(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return R.ok();
    }

    /**
     * 删除Banner
     *
     * @param id
     * @return
     */
    @ApiOperation("删除Banner")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        bannerService.removeById(id);
        return R.ok();
    }


}

