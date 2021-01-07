package com.yikao.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Student)实体类
 *
 * @author leri
 * @since 2021-01-07 10:22:41
 */
public class Student implements Serializable {
    private static final long serialVersionUID = -45419303171904407L;

    private Integer id;

    private String username;

    private String password;
    /**
     * 昵称
     */
    private String nikename;
    /**
     * 该用户所管理的课程
     */
    private String adminCourse;
    /**
     * qq
     */
    private String qq;
    /**
     * 手机号 可用于找回密码
     */
    private Integer phone;
    /**
     * 邮箱 可用于找回密码
     */
    private String email;
    /**
     * 禁言 封号等
     */
    private Integer status;

    private Date createtime;

    private Date updatetime;

    private String obj1;

    private String obj2;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    public String getAdminCourse() {
        return adminCourse;
    }

    public void setAdminCourse(String adminCourse) {
        this.adminCourse = adminCourse;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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