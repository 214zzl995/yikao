package com.yikao.demo.service.impl;

import com.yikao.demo.dao.QuestionCommentDao;
import com.yikao.demo.entity.QuestionComment;
import com.yikao.demo.service.QuestionCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (QuestionComment)表服务实现类
 *
 * @author leri
 * @since 2021-01-07 10:22:34
 */
@Service("questionCommentService")
public class QuestionCommentServiceImpl implements QuestionCommentService {
    @Resource
    private QuestionCommentDao questionCommentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public QuestionComment queryById(Integer id) {
        return this.questionCommentDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<QuestionComment> queryAllByLimit(int offset, int limit) {
        return this.questionCommentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param questionComment 实例对象
     * @return 实例对象
     */
    @Override
    public QuestionComment insert(QuestionComment questionComment) {
        this.questionCommentDao.insert(questionComment);
        return questionComment;
    }

    /**
     * 修改数据
     *
     * @param questionComment 实例对象
     * @return 实例对象
     */
    @Override
    public QuestionComment update(QuestionComment questionComment) {
        this.questionCommentDao.update(questionComment);
        return this.queryById(questionComment.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.questionCommentDao.deleteById(id) > 0;
    }
}