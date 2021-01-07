package com.yikao.demo.dao;

import com.yikao.demo.entity.MultipleChoice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (MultipleChoice)表数据库访问层
 *
 * @author leri
 * @since 2021-01-07 10:22:12
 */
public interface MultipleChoiceDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MultipleChoice queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<MultipleChoice> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param multipleChoice 实例对象
     * @return 对象列表
     */
    List<MultipleChoice> queryAll(MultipleChoice multipleChoice);

    /**
     * 新增数据
     *
     * @param multipleChoice 实例对象
     * @return 影响行数
     */
    int insert(MultipleChoice multipleChoice);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<MultipleChoice> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<MultipleChoice> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<MultipleChoice> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<MultipleChoice> entities);

    /**
     * 修改数据
     *
     * @param multipleChoice 实例对象
     * @return 影响行数
     */
    int update(MultipleChoice multipleChoice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}