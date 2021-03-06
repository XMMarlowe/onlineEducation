package com.marlowe.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.marlowe.commonutils.JwtUtils;
import com.marlowe.commonutils.R;
import com.marlowe.commonutils.ordervo.CourseWebVoOrder;
import com.marlowe.eduservice.client.OrdersClient;
import com.marlowe.eduservice.entity.EduCourse;
import com.marlowe.eduservice.entity.chapter.ChapterVo;
import com.marlowe.eduservice.entity.frontvo.CourseFrontVo;
import com.marlowe.eduservice.entity.vo.CourseWebVo;
import com.marlowe.eduservice.service.EduChapterService;
import com.marlowe.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: onlineEducation
 * @description:
 * @author: Marlowe
 * @create: 2021-07-27 20:45
 **/
@Api(tags = "课程前台控制器")
@RestController
@RequestMapping("/eduservice/coursefront")
//@CrossOrigin
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private OrdersClient ordersClient;

    /**
     * 条件查询带分页查询课程
     *
     * @return
     */
    @ApiOperation("条件查询带分页查询课程")
    @PostMapping("getFrontCourseList/{pageNo}/{pageSize}")
    public R getFrontCourseList(@PathVariable long pageNo, @PathVariable long pageSize, @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        // 创建一个page对象
        Page<EduCourse> pageCourse = new Page<>(pageNo, pageSize);

        // 构造条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        // 判断条件值是否为空，不为空拼接
        // 一二级分类排序
        if (!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())) {
            wrapper.eq("subject_parent_id", courseFrontVo.getSubjectParentId());
            if (!StringUtils.isEmpty(courseFrontVo.getSubjectId())) {
                wrapper.eq("subject_id", courseFrontVo.getSubjectId());
            }
        }

        // 销量排序
        if (!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())) {
            wrapper.orderByDesc("buy_count");
        }

        if (!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())) {
            wrapper.orderByDesc("gmt_create");
        }

        if (!StringUtils.isEmpty(courseFrontVo.getPriceSort())) {
            wrapper.orderByDesc("price");
        }

        if (!StringUtils.isEmpty(courseFrontVo.getPrice())) {
            // 0：免费 1：收费 没有值：查询全部
            if ("0".equals(courseFrontVo.getPrice())) {
                wrapper.eq("price", 0);
            } else if ("1".equals(courseFrontVo.getPrice())) {
                wrapper.gt("price", 0);
            }
        }

        courseService.page(pageCourse, wrapper);

        long total = pageCourse.getTotal();
        List<EduCourse> records = pageCourse.getRecords();
        return R.ok().data("total", total).data("rows", records);
    }

    /**
     * 获得前台课程详情,需要传token
     *
     * @return
     */
    @ApiOperation("获得前台课程详情，需要传token")
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {
        // 根据课程id，查询课程基本信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);

        // 根据课程id查询章节和小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);

        // 根据课程id和用户id查询当前课程是否已经支付过了
        boolean buyCourse = ordersClient.isBuyCourse(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data("courseWebVo", courseWebVo).data("chapterVideoList", chapterVideoList).data("isBuy", buyCourse);
    }

    /**
     * 订单模块远程调用：根据课程id查询课程信息
     *
     * @param id
     * @return
     */
    @ApiOperation("订单模块远程调用：根据课程id查询课程信息")
    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id) {
        CourseWebVo courseInfo = courseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseInfo, courseWebVoOrder);
        return courseWebVoOrder;
    }

    /**
     * 根据课程名称或者描述分页查询课程信息
     * @param keyWord
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation("根据课程名称或者描述分页查询课程信息")
    @GetMapping("getFrontCourseList/descOrTitle/{keyWord}/{pageNo}/{pageSize}")
    public R getFrontCourseList(@PathVariable String keyWord, @PathVariable long pageNo, @PathVariable long pageSize) {
        // 创建一个page对象
        Page<EduCourse> pageCourse = new Page<>(pageNo, pageSize);

        // 构造条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

        wrapper.like("title", keyWord).or().like("description", keyWord);
        courseService.page(pageCourse, wrapper);

        long total = pageCourse.getTotal();
        List<EduCourse> records = pageCourse.getRecords();
        return R.ok().data("total", total).data("rows", records);
    }
}
