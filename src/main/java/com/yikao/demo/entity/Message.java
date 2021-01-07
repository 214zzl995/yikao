package com.yikao.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Message)实体类
 *
 * @author leri
 * @since 2021-01-07 10:22:07
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 143803848937585958L;

    private Integer id;
    /**
     * 发起人
     */
    private Integer myUserId;
    /**
     * 接受人
     */
    private Integer forUserId;
    /**
     * 消息主体 json
     */
    private String message;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 更新时间
     */
    private Date updatetime;
    /**
     * 在某个课程进行的私聊
     */
    private Integer courseid;

    private String obj1;

    private String obj2;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMyUserId() {
        return myUserId;
    }

    public void setMyUserId(Integer myUserId) {
        this.myUserId = myUserId;
    }

    public Integer getForUserId() {
        return forUserId;
    }

    public void setForUserId(Integer forUserId) {
        this.forUserId = forUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
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