package com.yikao.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (QuestionComment)实体类
 *
 * @author leri
 * @since 2021-01-07 10:22:31
 */
public class QuestionComment implements Serializable {
    private static final long serialVersionUID = -18608368741725617L;

    private Integer id;
    /**
     * 判断  题型 1.选择 2.判断 3.填空 4.简答
     */
    private Integer questionType;
    /**
     * 在题型中的id
     */
    private Integer questionId;
    /**
     * 回复的人
     */
    private Integer userId;
    /**
     * 回复的内容
     */
    private String userComment;
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

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
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