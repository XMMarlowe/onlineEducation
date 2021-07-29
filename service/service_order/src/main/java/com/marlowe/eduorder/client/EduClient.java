package com.marlowe.eduorder.client;

import com.marlowe.commonutils.ordervo.CourseWebVoOrder;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @program: education_parent
 * @description:
 * @author: Marlowe
 * @create: 2021-07-28 21:22
 **/
@Component
@FeignClient("service-edu")
public interface EduClient {

    /**
     * 根据课程id查询课程信息
     *
     * @param id
     * @return
     */
    @ApiOperation("根据课程id查询课程信息")
    @PostMapping("/eduservice/coursefront/getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable("id") String id);
}
