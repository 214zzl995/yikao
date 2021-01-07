package com.yikao.demo.service.impl;

import com.yikao.demo.dao.WrongQuestionDao;
import com.yikao.demo.entity.WrongQuestion;
import com.yikao.demo.service.WrongQuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (WrongQuestion)表服务实现类
 *
 * @author leri
 * @since 2021-01-07 10:22:52
 */
@Service("wrongQuestionService")
public class WrongQuestionServiceImpl implements WrongQuestionService {
    @Resource
    private WrongQuestionDao wrongQuestionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public WrongQuestion queryById(Integer id) {
        return this.wrongQuestionDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<WrongQuestion> queryAllByLimit(int offset, int limit) {
        return this.wrongQuestionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param wrongQuestion 实例对象
     * @return 实例对象
     */
    @Override
    public WrongQuestion insert(WrongQuestion wrongQuestion) {
        this.wrongQuestionDao.insert(wrongQuestion);
        return wrongQuestion;
    }

    /**
     * 修改数据
     *
     * @param wrongQuestion 实例对象
     * @return 实例对象
     */
    @Override
    public WrongQuestion update(WrongQuestion wrongQuestion) {
        this.wrongQuestionDao.update(wrongQuestion);
        return this.queryById(wrongQuestion.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.wrongQuestionDao.deleteById(id) > 0;
    }
}