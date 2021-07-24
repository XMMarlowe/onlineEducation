package com.marlowe.educms.service;

import com.marlowe.educms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-24
 */
public interface CrmBannerService extends IService<CrmBanner> {

    /**
     * 获取首页所有banner
     *
     * @return
     */
    List<CrmBanner> selectAllBanner();

}
