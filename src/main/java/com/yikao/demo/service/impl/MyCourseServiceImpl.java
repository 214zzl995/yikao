package com.yikao.demo.service.impl;

import com.yikao.demo.dao.MyCourseDao;
import com.yikao.demo.entity.MyCourse;
import com.yikao.demo.service.MyCourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (MyCourse)表服务实现类
 *
 * @author leri
 * @since 2021-01-07 10:22:17
 */
@Service("myCourseService")
public class MyCourseServiceImpl implements MyCourseService {
    @Resource
    private MyCourseDao myCourseDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MyCourse queryById(Integer id) {
        return this.myCourseDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<MyCourse> queryAllByLimit(int offset, int limit) {
        return this.myCourseDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param myCourse 实例对象
     * @return 实例对象
     */
    @Override
    public MyCourse insert(MyCourse myCourse) {
        this.myCourseDao.insert(myCourse);
        return myCourse;
    }

    /**
     * 修改数据
     *
     * @param myCourse 实例对象
     * @return 实例对象
     */
    @Override
    public MyCourse update(MyCourse myCourse) {
        this.myCourseDao.update(myCourse);
        return this.queryById(myCourse.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.myCourseDao.deleteById(id) > 0;
    }
}