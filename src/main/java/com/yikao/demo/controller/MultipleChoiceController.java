package com.yikao.demo.controller;

import com.yikao.demo.entity.MultipleChoice;
import com.yikao.demo.service.MultipleChoiceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (MultipleChoice)表控制层
 *
 * @author leri
 * @since 2021-01-07 10:22:14
 */
@RestController
@RequestMapping("multipleChoice")
public class MultipleChoiceController {
    /**
     * 服务对象
     */
    @Resource
    private MultipleChoiceService multipleChoiceService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public MultipleChoice selectOne(Integer id) {
        return this.multipleChoiceService.queryById(id);
    }

}