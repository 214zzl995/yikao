package com.yikao.demo.service.impl;

import com.yikao.demo.dao.SingleChoiceDao;
import com.yikao.demo.entity.SingleChoice;
import com.yikao.demo.service.SingleChoiceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SingleChoice)表服务实现类
 *
 * @author leri
 * @since 2021-01-07 10:22:39
 */
@Service("singleChoiceService")
public class SingleChoiceServiceImpl implements SingleChoiceService {
    @Resource
    private SingleChoiceDao singleChoiceDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SingleChoice queryById(Integer id) {
        return this.singleChoiceDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SingleChoice> queryAllByLimit(int offset, int limit) {
        return this.singleChoiceDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param singleChoice 实例对象
     * @return 实例对象
     */
    @Override
    public SingleChoice insert(SingleChoice singleChoice) {
        this.singleChoiceDao.insert(singleChoice);
        return singleChoice;
    }

    /**
     * 修改数据
     *
     * @param singleChoice 实例对象
     * @return 实例对象
     */
    @Override
    public SingleChoice update(SingleChoice singleChoice) {
        this.singleChoiceDao.update(singleChoice);
        return this.queryById(singleChoice.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.singleChoiceDao.deleteById(id) > 0;
    }
}