package com.yikao.demo.dao;

import com.yikao.demo.entity.MyCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (MyCourse)表数据库访问层
 *
 * @author leri
 * @since 2021-01-07 10:22:16
 */
public interface MyCourseDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MyCourse queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<MyCourse> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param myCourse 实例对象
     * @return 对象列表
     */
    List<MyCourse> queryAll(MyCourse myCourse);

    /**
     * 新增数据
     *
     * @param myCourse 实例对象
     * @return 影响行数
     */
    int insert(MyCourse myCourse);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<MyCourse> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<MyCourse> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<MyCourse> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<MyCourse> entities);

    /**
     * 修改数据
     *
     * @param myCourse 实例对象
     * @return 影响行数
     */
    int update(MyCourse myCourse);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}