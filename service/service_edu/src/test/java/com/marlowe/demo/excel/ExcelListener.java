package com.marlowe.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @program: education_parent
 * @description:
 * @author: Marlowe
 * @create: 2021-07-19 22:34
 **/
public class ExcelListener extends AnalysisEventListener<DemoDate> {
    /**
     * 按行读取
     *
     * @param data
     * @param analysisContext
     */
    @Override
    public void invoke(DemoDate data, AnalysisContext analysisContext) {
        System.out.println("*********" + data);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头：" + headMap);
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
