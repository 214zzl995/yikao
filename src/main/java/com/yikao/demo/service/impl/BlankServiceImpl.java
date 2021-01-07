package com.yikao.demo.service.impl;

import com.yikao.demo.dao.BlankDao;
import com.yikao.demo.entity.Blank;
import com.yikao.demo.service.BlankService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Blank)表服务实现类
 *
 * @author leri
 * @since 2021-01-07 10:21:50
 */
@Service("blankService")
public class BlankServiceImpl implements BlankService {
    @Resource
    private BlankDao blankDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Blank queryById(Integer id) {
        return this.blankDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Blank> queryAllByLimit(int offset, int limit) {
        return this.blankDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param blank 实例对象
     * @return 实例对象
     */
    @Override
    public Blank insert(Blank blank) {
        this.blankDao.insert(blank);
        return blank;
    }

    /**
     * 修改数据
     *
     * @param blank 实例对象
     * @return 实例对象
     */
    @Override
    public Blank update(Blank blank) {
        this.blankDao.update(blank);
        return this.queryById(blank.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.blankDao.deleteById(id) > 0;
    }
}