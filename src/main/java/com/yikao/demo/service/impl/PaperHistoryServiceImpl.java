package com.yikao.demo.service.impl;

import com.yikao.demo.dao.PaperHistoryDao;
import com.yikao.demo.entity.PaperHistory;
import com.yikao.demo.service.PaperHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (PaperHistory)表服务实现类
 *
 * @author leri
 * @since 2021-01-07 10:22:28
 */
@Service("paperHistoryService")
public class PaperHistoryServiceImpl implements PaperHistoryService {
    @Resource
    private PaperHistoryDao paperHistoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PaperHistory queryById(Integer id) {
        return this.paperHistoryDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<PaperHistory> queryAllByLimit(int offset, int limit) {
        return this.paperHistoryDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param paperHistory 实例对象
     * @return 实例对象
     */
    @Override
    public PaperHistory insert(PaperHistory paperHistory) {
        this.paperHistoryDao.insert(paperHistory);
        return paperHistory;
    }

    /**
     * 修改数据
     *
     * @param paperHistory 实例对象
     * @return 实例对象
     */
    @Override
    public PaperHistory update(PaperHistory paperHistory) {
        this.paperHistoryDao.update(paperHistory);
        return this.queryById(paperHistory.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.paperHistoryDao.deleteById(id) > 0;
    }
}