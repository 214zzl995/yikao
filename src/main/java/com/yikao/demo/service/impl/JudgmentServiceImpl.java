package com.yikao.demo.service.impl;

import com.yikao.demo.dao.JudgmentDao;
import com.yikao.demo.entity.Judgment;
import com.yikao.demo.service.JudgmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Judgment)表服务实现类
 *
 * @author leri
 * @since 2021-01-07 10:22:05
 */
@Service("judgmentService")
public class JudgmentServiceImpl implements JudgmentService {
    @Resource
    private JudgmentDao judgmentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Judgment queryById(Integer id) {
        return this.judgmentDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Judgment> queryAllByLimit(int offset, int limit) {
        return this.judgmentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param judgment 实例对象
     * @return 实例对象
     */
    @Override
    public Judgment insert(Judgment judgment) {
        this.judgmentDao.insert(judgment);
        return judgment;
    }

    /**
     * 修改数据
     *
     * @param judgment 实例对象
     * @return 实例对象
     */
    @Override
    public Judgment update(Judgment judgment) {
        this.judgmentDao.update(judgment);
        return this.queryById(judgment.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.judgmentDao.deleteById(id) > 0;
    }
}