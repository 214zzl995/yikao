package com.yikao.demo.controller;

import com.yikao.demo.entity.SingleChoice;
import com.yikao.demo.service.SingleChoiceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (SingleChoice)表控制层
 *
 * @author leri
 * @since 2021-01-07 10:22:40
 */
@RestController
@RequestMapping("singleChoice")
public class SingleChoiceController {
    /**
     * 服务对象
     */
    @Resource
    private SingleChoiceService singleChoiceService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SingleChoice selectOne(Integer id) {
        return this.singleChoiceService.queryById(id);
    }

}