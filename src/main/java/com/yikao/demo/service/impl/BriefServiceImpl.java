package com.yikao.demo.service.impl;

import com.yikao.demo.dao.BriefDao;
import com.yikao.demo.entity.Brief;
import com.yikao.demo.service.BriefService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Brief)表服务实现类
 *
 * @author leri
 * @since 2021-01-07 10:21:54
 */
@Service("briefService")
public class BriefServiceImpl implements BriefService {
    @Resource
    private BriefDao briefDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Brief queryById(Integer id) {
        return this.briefDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Brief> queryAllByLimit(int offset, int limit) {
        return this.briefDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param brief 实例对象
     * @return 实例对象
     */
    @Override
    public Brief insert(Brief brief) {
        this.briefDao.insert(brief);
        return brief;
    }

    /**
     * 修改数据
     *
     * @param brief 实例对象
     * @return 实例对象
     */
    @Override
    public Brief update(Brief brief) {
        this.briefDao.update(brief);
        return this.queryById(brief.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.briefDao.deleteById(id) > 0;
    }
}