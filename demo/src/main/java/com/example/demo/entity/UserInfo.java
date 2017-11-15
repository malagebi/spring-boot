package com.example.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lishunli
 * @create 2017-11-14 9:55
 **/
public class UserInfo implements Serializable{

    @JSONField(serialize=false)
    private Long  id;

    private String userName;

    @JSONField(format = "yyy-MM-dd hh:mm:ss")
    private Date  createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
