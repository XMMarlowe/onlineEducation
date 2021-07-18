package com.marlowe.servicebase.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: education_parent
 * @description:
 * @author: Marlowe
 * @create: 2021-07-18 08:19
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuliException extends RuntimeException {

    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码")
    private Integer code;

    /**
     * 异常信息
     */
    private String msg;
}
