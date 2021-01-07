package com.yikao.demo.controller;

import com.yikao.demo.entity.Course;
import com.yikao.demo.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Course)表控制层
 *
 * @author leri
 * @since 2021-01-07 10:21:57
 */
@RestController
@RequestMapping("course")
public class CourseController {
    /**
     * 服务对象
     */
    @Resource
    private CourseService courseService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Course selectOne(Integer id) {
        return this.courseService.queryById(id);
    }

}