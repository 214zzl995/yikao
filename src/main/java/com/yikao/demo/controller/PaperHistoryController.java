package com.yikao.demo.controller;

import com.yikao.demo.entity.PaperHistory;
import com.yikao.demo.service.PaperHistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (PaperHistory)表控制层
 *
 * @author leri
 * @since 2021-01-07 10:22:29
 */
@RestController
@RequestMapping("paperHistory")
public class PaperHistoryController {
    /**
     * 服务对象
     */
    @Resource
    private PaperHistoryService paperHistoryService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public PaperHistory selectOne(Integer id) {
        return this.paperHistoryService.queryById(id);
    }

}