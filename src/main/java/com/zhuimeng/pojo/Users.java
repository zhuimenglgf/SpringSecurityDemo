package com.zhuimeng.pojo;

import java.util.Date;

public class Users {
    private Integer userid;

    private String username;

    private String password;

    private Short status;

    private Date createon;

    private Date updateon;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getCreateon() {
        return createon;
    }

    public void setCreateon(Date createon) {
        this.createon = createon;
    }

    public Date getUpdateon() {
        return updateon;
    }

    public void setUpdateon(Date updateon) {
        this.updateon = updateon;
    }
}