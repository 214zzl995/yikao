package com.yikao.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (PaperHistory)实体类
 *
 * @author leri
 * @since 2021-01-07 10:22:25
 */
public class PaperHistory implements Serializable {
    private static final long serialVersionUID = -54701347523882673L;

    private Integer id;
    /**
     * 答题人
     */
    private Integer userId;
    /**
     * 试卷id
     */
    private Integer paperId;
    /**
     * 我的答案 每题用逗号隔开
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

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
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