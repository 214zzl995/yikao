package com.yikao.demo.controller;

import com.yikao.demo.entity.WrongQuestion;
import com.yikao.demo.service.WrongQuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (WrongQuestion)表控制层
 *
 * @author leri
 * @since 2021-01-07 10:22:52
 */
@RestController
@RequestMapping("wrongQuestion")
public class WrongQuestionController {
    /**
     * 服务对象
     */
    @Resource
    private WrongQuestionService wrongQuestionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public WrongQuestion selectOne(Integer id) {
        return this.wrongQuestionService.queryById(id);
    }

}