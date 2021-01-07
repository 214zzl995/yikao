package com.yikao.demo.service.impl;

import com.yikao.demo.dao.CourseCommentDao;
import com.yikao.demo.entity.CourseComment;
import com.yikao.demo.service.CourseCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (CourseComment)表服务实现类
 *
 * @author leri
 * @since 2021-01-07 10:21:59
 */
@Service("courseCommentService")
public class CourseCommentServiceImpl implements CourseCommentService {
    @Resource
    private CourseCommentDao courseCommentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CourseComment queryById(Integer id) {
        return this.courseCommentDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<CourseComment> queryAllByLimit(int offset, int limit) {
        return this.courseCommentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param courseComment 实例对象
     * @return 实例对象
     */
    @Override
    public CourseComment insert(CourseComment courseComment) {
        this.courseCommentDao.insert(courseComment);
        return courseComment;
    }

    /**
     * 修改数据
     *
     * @param courseComment 实例对象
     * @return 实例对象
     */
    @Override
    public CourseComment update(CourseComment courseComment) {
        this.courseCommentDao.update(courseComment);
        return this.queryById(courseComment.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.courseCommentDao.deleteById(id) > 0;
    }
}