package com.yikao.demo.controller;

import com.yikao.demo.entity.Judgment;
import com.yikao.demo.service.JudgmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Judgment)表控制层
 *
 * @author leri
 * @since 2021-01-07 10:22:06
 */
@RestController
@RequestMapping("judgment")
public class JudgmentController {
    /**
     * 服务对象
     */
    @Resource
    private JudgmentService judgmentService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Judgment selectOne(Integer id) {
        return this.judgmentService.queryById(id);
    }

}