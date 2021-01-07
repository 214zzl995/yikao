package com.yikao.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Course)实体类
 *
 * @author leri
 * @since 2021-01-07 10:21:55
 */
public class Course implements Serializable {
    private static final long serialVersionUID = 149222080878183837L;

    private Integer id;
    /**
     * 老师的id
     */
    private Integer userId;
    /**
     * 课程名字
     */
    private String courseTitle;
    /**
     * 课程简介
     */
    private String courseIntroduction;
    /**
     * 教师名称
     */
    private String teacherName;
    /**
     * 课程图片
     */
    private String courseImage;
    /**
     * 课程类型
     */
    private Integer courseType;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 更新时间
     */
    private Date updatetime;
    /**
     * 是否允许私聊
     */
    private Integer private

    private String obj1;

    private String obj2;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseIntroduction() {
        return courseIntroduction;
    }

    public void setCourseIntroduction(String courseIntroduction) {
        this.courseIntroduction = courseIntroduction;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(String courseImage) {
        this.courseImage = courseImage;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getPrivate() {
        return private
    }

    public void setPrivate(Integer private) {
        this. private =private
    }

    public String getObj1() {
        return obj1;
    }

    public void setObj1(String obj1) {
        this.obj1 = obj1;
    }

    public String getObj2() {
        return obj2;
    }

    public void setObj2(String obj2) {
        this.obj2 = obj2;
    }

}