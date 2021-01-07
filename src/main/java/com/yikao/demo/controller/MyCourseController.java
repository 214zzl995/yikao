package com.yikao.demo.controller;

import com.yikao.demo.entity.MyCourse;
import com.yikao.demo.service.MyCourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (MyCourse)表控制层
 *
 * @author leri
 * @since 2021-01-07 10:22:19
 */
@RestController
@RequestMapping("myCourse")
public class MyCourseController {
    /**
     * 服务对象
     */
    @Resource
    private MyCourseService myCourseService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public MyCourse selectOne(Integer id) {
        return this.myCourseService.queryById(id);
    }

}