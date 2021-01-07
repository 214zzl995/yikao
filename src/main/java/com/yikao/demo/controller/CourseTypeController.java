package com.yikao.demo.controller;

import com.yikao.demo.entity.CourseType;
import com.yikao.demo.service.CourseTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (CourseType)表控制层
 *
 * @author leri
 * @since 2021-01-07 10:22:02
 */
@RestController
@RequestMapping("courseType")
public class CourseTypeController {
    /**
     * 服务对象
     */
    @Resource
    private CourseTypeService courseTypeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public CourseType selectOne(Integer id) {
        return this.courseTypeService.queryById(id);
    }

}