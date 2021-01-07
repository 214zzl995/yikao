package com.yikao.demo.service.impl;

import com.yikao.demo.dao.CourseTypeDao;
import com.yikao.demo.entity.CourseType;
import com.yikao.demo.service.CourseTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (CourseType)表服务实现类
 *
 * @author leri
 * @since 2021-01-07 10:22:02
 */
@Service("courseTypeService")
public class CourseTypeServiceImpl implements CourseTypeService {
    @Resource
    private CourseTypeDao courseTypeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CourseType queryById(Integer id) {
        return this.courseTypeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<CourseType> queryAllByLimit(int offset, int limit) {
        return this.courseTypeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param courseType 实例对象
     * @return 实例对象
     */
    @Override
    public CourseType insert(CourseType courseType) {
        this.courseTypeDao.insert(courseType);
        return courseType;
    }

    /**
     * 修改数据
     *
     * @param courseType 实例对象
     * @return 实例对象
     */
    @Override
    public CourseType update(CourseType courseType) {
        this.courseTypeDao.update(courseType);
        return this.queryById(courseType.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.courseTypeDao.deleteById(id) > 0;
    }
}