package com.marlowe.educenter.mapper;

import com.marlowe.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-26
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    /**
     * 查询某一天注册的人数
     *
     * @param day
     * @return
     */
    Integer countRegisterDay(String day);
}
