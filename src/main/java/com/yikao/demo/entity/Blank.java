package com.yikao.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Blank)实体类
 *
 * @author leri
 * @since 2021-01-07 10:21:47
 */
public class Blank implements Serializable {
    private static final long serialVersionUID = 494353598268601154L;

    private Integer id;

    private Integer userId;
    /**
     * 课程id
     */
    private Integer courseId;
    /**
     * 问题
     */
    private String question;
    /**
     * 题目是否公开
     */
    private Integer public
    /**
     * 多个答案逗号隔开 一个匹配即可
     */
    private String answer;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 更新时间
     */
    private Date updatetime;

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

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getPublic() {
        return public
    }

    public void setPublic(Integer public) {
        this. public =public
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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