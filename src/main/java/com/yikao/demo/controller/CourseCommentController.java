package com.yikao.demo.controller;

import com.yikao.demo.entity.CourseComment;
import com.yikao.demo.service.CourseCommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (CourseComment)表控制层
 *
 * @author leri
 * @since 2021-01-07 10:21:59
 */
@RestController
@RequestMapping("courseComment")
public class CourseCommentController {
    /**
     * 服务对象
     */
    @Resource
    private CourseCommentService courseCommentService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public CourseComment selectOne(Integer id) {
        return this.courseCommentService.queryById(id);
    }

}