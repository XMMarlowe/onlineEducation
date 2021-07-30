package com.marlowe.educenter.service;

import com.marlowe.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.marlowe.educenter.entity.vo.LoginVo;
import com.marlowe.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-26
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    /**
     * 登录
     *
     * @param member
     * @return
     */
    String login(LoginVo member);

    /**
     * 注册
     *
     * @param registerVo
     */
    void register(RegisterVo registerVo);

    /**
     * 查询某一天注册的人数
     *
     * @param day
     * @return
     */
    Integer countRegisterDay(String day);

}
