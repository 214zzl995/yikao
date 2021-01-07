package com.yikao.demo.controller;

import com.yikao.demo.entity.Brief;
import com.yikao.demo.service.BriefService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Brief)表控制层
 *
 * @author leri
 * @since 2021-01-07 10:21:54
 */
@RestController
@RequestMapping("brief")
public class BriefController {
    /**
     * 服务对象
     */
    @Resource
    private BriefService briefService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Brief selectOne(Integer id) {
        return this.briefService.queryById(id);
    }

}