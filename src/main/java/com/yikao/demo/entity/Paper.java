package com.yikao.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Paper)实体类
 *
 * @author leri
 * @since 2021-01-07 10:22:20
 */
public class Paper implements Serializable {
    private static final long serialVersionUID = 469227733742418719L;

    private Integer id;
    /**
     * 出卷人
     */
    private Integer userId;
    /**
     * 课程id
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
     * 试卷名称
     */
    private String paperName;
    /**
     * 考试时限（毫秒计数）
     */
    private Integer timeLimit;
    /**
     * 考试时效（过时不候）
     */
    private Date aging;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 更新时间
     */
    private Date updatetime;
    /**
     * 收费试卷（限定）
     */
    private Double price;

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

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Date getAging() {
        return aging;
    }

    public void setAging(Date aging) {
        this.aging = aging;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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