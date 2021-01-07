package com.yikao.demo.service;

import com.yikao.demo.entity.CourseType;

import java.util.List;

/**
 * (CourseType)表服务接口
 *
 * @author leri
 * @since 2021-01-07 10:22:01
 */
public interface CourseTypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CourseType queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<CourseType> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param courseType 实例对象
     * @return 实例对象
     */
    CourseType insert(CourseType courseType);

    /**
     * 修改数据
     *
     * @param courseType 实例对象
     * @return 实例对象
     */
    CourseType update(CourseType courseType);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}