package com.yikao.demo.controller;

import com.yikao.demo.entity.Blank;
import com.yikao.demo.service.BlankService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Blank)表控制层
 *
 * @author leri
 * @since 2021-01-07 10:21:51
 */
@RestController
@RequestMapping("blank")
public class BlankController {
    /**
     * 服务对象
     */
    @Resource
    private BlankService blankService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Blank selectOne(Integer id) {
        return this.blankService.queryById(id);
    }

}