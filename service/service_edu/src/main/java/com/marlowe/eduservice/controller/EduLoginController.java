package com.marlowe.eduservice.controller;

import com.marlowe.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @program: education_parent
 * @description:
 * @author: Marlowe
 * @create: 2021-07-18 09:57
 **/
@CrossOrigin
@Api(tags = "登录控制器")
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {

    @PostMapping("login")
    public R login() {

        return R.ok().data("token", "admin");
    }


    @GetMapping("info")
    public R info() {

        return R.ok().data("roles","roles").data("name","admin").data("avator","https://baomidou.com/img/logo.svg");
    }


}
