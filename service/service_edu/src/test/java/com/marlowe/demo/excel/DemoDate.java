package com.marlowe.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @program: education_parent
 * @description:
 * @author: Marlowe
 * @create: 2021-07-19 22:11
 **/
@Data
public class DemoDate {

    @ExcelProperty(value = "学生编号",index = 0)
    private Integer sno;

    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;
}
