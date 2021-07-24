package com.marlowe.educms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marlowe.educms.entity.CrmBanner;
import com.marlowe.educms.mapper.CrmBannerMapper;
import com.marlowe.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-24
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    /**
     * 获取首页所有banner
     *
     * @return
     */
    @Cacheable(value = "banner", key = "'selectIndexList'")
    @Override
    public List<CrmBanner> selectAllBanner() {
        // 根据创建时间进行降序排列，显示排列后前两条记录
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");

        // last方法，拼接sql语句
        wrapper.last("limit 2");
        List<CrmBanner> list = baseMapper.selectList(wrapper);
        return list;
    }
}
