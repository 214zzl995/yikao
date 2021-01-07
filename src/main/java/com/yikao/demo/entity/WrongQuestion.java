package com.yikao.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (WrongQuestion)实体类
 *
 * @author leri
 * @since 2021-01-07 10:22:51
 */
public class WrongQuestion implements Serializable {
    private static final long serialVersionUID = -44019387302365847L;

    private Integer id;
    /**
     * 这是谁的错题
     */
    private Integer userId;
    /**
     * 属于哪种类型
     */
    private Integer typeId;
    /**
     * 来着哪门课程
     */
    private Integer courseId;
    /**
     * 判断  题型 1.选择 2.判断 3.填空 4.简答
     */
    private String questionType;
    /**
     * 在题型表中的id
     */
    private String questionId;
    /**
     * 错误的答案
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
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