package com.marlowe.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: education_parent
 * @description: Excel写操作
 * @author: Marlowe
 * @create: 2021-07-19 22:22
 **/
public class TestEasyExcel {

    public static void main(String[] args) {
//        // 实现excel写操作
//
//        // 1. 设置写入文件夹地址和excel的名称
//        String filename = "D:/write.xlsx";
//
//        // 2. 调用easyExcel里面的方法实现写操作
//        EasyExcel.write(filename, DemoDate.class).sheet("学生列表").doWrite(getData());

        // 实现excel读操作

        String filename = "D:/write.xlsx";

        EasyExcel.read(filename, DemoDate.class, new ExcelListener()).sheet().doRead();

    }

    private static List<DemoDate> getData() {
        List<DemoDate> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoDate demoDate = new DemoDate();
            demoDate.setSno(i);
            demoDate.setSname("lucy" + i);
            list.add(demoDate);
        }
        return list;
    }
}
