package com.marlowe.msmservice.controller;

import com.marlowe.commonutils.R;
import com.marlowe.msmservice.service.MsmService;
import com.marlowe.msmservice.utils.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;


/**
 * @program: education_parent
 * @description:
 * @author: Marlowe
 * @create: 2021-07-25 17:27
 **/
@Api(tags = "短信服务控制器")
@RequestMapping("/edumsm/msm")
@RestController
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 发送短信的方法
     *
     * @return
     */
    @ApiOperation("发送短信的方法")
    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone) {
        // 从redis获取验证码，如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return R.ok();
        }

        // 生成随机值，传递给阿里云进行发送
        code = RandomUtil.getFourBitRandom();

        // 调用service发送短信的方法
        boolean isSend = msmService.send(code, phone);
        if (isSend) {
            // 发送成功，把发送成功验证码放到redis中，并设置有效时间
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return R.ok();
        }
        return R.error().message("短信发送失败");
    }
}
