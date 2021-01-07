package com.yikao.demo.service;

import com.yikao.demo.entity.WrongQuestion;

import java.util.List;

/**
 * (WrongQuestion)表服务接口
 *
 * @author leri
 * @since 2021-01-07 10:22:52
 */
public interface WrongQuestionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WrongQuestion queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<WrongQuestion> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param wrongQuestion 实例对象
     * @return 实例对象
     */
    WrongQuestion insert(WrongQuestion wrongQuestion);

    /**
     * 修改数据
     *
     * @param wrongQuestion 实例对象
     * @return 实例对象
     */
    WrongQuestion update(WrongQuestion wrongQuestion);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}