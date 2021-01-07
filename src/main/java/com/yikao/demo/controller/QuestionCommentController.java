package com.yikao.demo.controller;

import com.yikao.demo.entity.QuestionComment;
import com.yikao.demo.service.QuestionCommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (QuestionComment)表控制层
 *
 * @author leri
 * @since 2021-01-07 10:22:35
 */
@RestController
@RequestMapping("questionComment")
public class QuestionCommentController {
    /**
     * 服务对象
     */
    @Resource
    private QuestionCommentService questionCommentService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public QuestionComment selectOne(Integer id) {
        return this.questionCommentService.queryById(id);
    }

}