package com.marlowe.educenter.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: education_parent
 * @description:
 * @author: Marlowe
 * @create: 2021-07-26 13:58
 **/
@Data
@ApiModel(value = "登录对象",description = "登录对象")
public class LoginVo {

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;
}
