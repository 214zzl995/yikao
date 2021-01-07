package com.yikao.demo.controller;

import com.yikao.demo.entity.Paper;
import com.yikao.demo.service.PaperService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Paper)表控制层
 *
 * @author leri
 * @since 2021-01-07 10:22:23
 */
@RestController
@RequestMapping("paper")
public class PaperController {
    /**
     * 服务对象
     */
    @Resource
    private PaperService paperService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Paper selectOne(Integer id) {
        return this.paperService.queryById(id);
    }

}