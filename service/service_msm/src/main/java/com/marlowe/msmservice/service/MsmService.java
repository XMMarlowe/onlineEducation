package com.marlowe.msmservice.service;

import java.util.HashMap;

/**
 * @program: education_parent
 * @description:
 * @author: Marlowe
 * @create: 2021-07-25 17:28
 **/
public interface MsmService {
    /**
     * 发送短信的方法
     *
     * @param code
     * @param phone
     * @return
     */
    boolean send(String code, String phone);
}
