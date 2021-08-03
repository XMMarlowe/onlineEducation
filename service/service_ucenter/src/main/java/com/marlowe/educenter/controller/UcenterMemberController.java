package com.marlowe.educenter.controller;


import com.marlowe.commonutils.JwtUtils;
import com.marlowe.commonutils.R;
import com.marlowe.commonutils.ordervo.UcenterMemberOrder;
import com.marlowe.educenter.entity.UcenterMember;
import com.marlowe.educenter.entity.vo.LoginVo;
import com.marlowe.educenter.entity.vo.RegisterVo;
import com.marlowe.educenter.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-26
 */
@Api(tags = "登录相关管理控制器")
@RestController
@RequestMapping("/educenter/member")
//@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    /**
     * 登录
     *
     * @param member
     * @return
     */
    @ApiOperation("登录")
    @PostMapping("login")
    public R login(@RequestBody LoginVo member) {
        // 返回token值，使用jwt生成
        String token = memberService.login(member);
        return R.ok().data("token", token);
    }

    /**
     * 注册
     *
     * @param registerVo
     * @return
     */
    @ApiOperation("注册")
    @PostMapping("register")
    public R registerUser(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }

    /**
     * 根据token获取用户信息
     *
     * @param request
     * @return
     */
    @ApiOperation("根据token获取用户信息")
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        // 调用jwt工具类的方法，根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        // 查询数据库，根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);
        return R.ok().data("userInfo", member);
    }

    /**
     * 订单模块远程调用：根据用户id获取用户信息
     *
     * @param id
     * @return
     */
    @ApiOperation("订单模块远程调用：根据用户id获取用户信息")
    @PostMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id) {
        UcenterMember member = memberService.getById(id);
        // 把member对象里面的值复制给UcenterMemberOrder
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member, ucenterMemberOrder);
        return ucenterMemberOrder;
    }

    /**
     * 查询某一天注册的人数
     * @param day
     * @return
     */
    @ApiOperation("查询某一天注册的人数")
    @GetMapping("countRegister/{day}")
    public R countRegister(@PathVariable String day) {
        Integer count = memberService.countRegisterDay(day);
        return R.ok().data("countRegister", count);
    }
}


