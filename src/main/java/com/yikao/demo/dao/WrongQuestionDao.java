package com.yikao.demo.dao;

import com.yikao.demo.entity.WrongQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (WrongQuestion)表数据库访问层
 *
 * @author leri
 * @since 2021-01-07 10:22:51
 */
public interface WrongQuestionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WrongQuestion queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<WrongQuestion> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param wrongQuestion 实例对象
     * @return 对象列表
     */
    List<WrongQuestion> queryAll(WrongQuestion wrongQuestion);

    /**
     * 新增数据
     *
     * @param wrongQuestion 实例对象
     * @return 影响行数
     */
    int insert(WrongQuestion wrongQuestion);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<WrongQuestion> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<WrongQuestion> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<WrongQuestion> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<WrongQuestion> entities);

    /**
     * 修改数据
     *
     * @param wrongQuestion 实例对象
     * @return 影响行数
     */
    int update(WrongQuestion wrongQuestion);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}