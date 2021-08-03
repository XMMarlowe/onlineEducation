package com.marlowe.eduorder.controller;


import com.marlowe.commonutils.R;
import com.marlowe.eduorder.service.PayLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author Marlowe
 * @since 2021-07-29
 */
@RestController
@RequestMapping("/eduorder/paylog")
//@CrossOrigin
public class PayLogController {

    @Autowired
    private PayLogService payLogService;

    /**
     * 根据订单号生成微信支付二维码
     *
     * @param orderNo
     * @return
     */
    @ApiOperation("根据订单号生成微信支付二维码")
    @GetMapping("createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo) {
        //返回信息，包含二维码地址，还有其他需要的信息
        Map map = payLogService.createNative(orderNo);
        System.out.println("****返回二维码map集合:" + map);
        return R.ok().data(map);
    }

    /**
     * 根据订单号查询订单支付状态
     *
     * @param orderNo
     * @return
     */
    @ApiOperation("根据订单号查询订单支付状态")
    @GetMapping("queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo) {
        Map<String, String> map = payLogService.queryPayStatus(orderNo);
        System.out.println("*****查询订单状态map集合:" + map);
        if (map == null) {
            return R.error().message("支付出错了");
        }
        //如果返回map里面不为空，通过map获取订单状态
        //支付成功
        if ("SUCCESS".equals(map.get("trade_state"))) {
            //添加记录到支付表，更新订单表订单状态
            payLogService.updateOrdersStatus(map);
            return R.ok().message("支付成功");
        }
        return R.ok().code(25000).message("支付中");
    }

}

