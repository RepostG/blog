package com.yc.education.model;

import javax.persistence.*;

public class Admins {
    /**
     * 管理员表
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String loginname;

    private String password;

    /**
     * 获取管理员表
     *
     * @return id - 管理员表
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置管理员表
     *
     * @param id 管理员表
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return loginname
     */
    public String getLoginname() {
        return loginname;
    }

    /**
     * @param loginname
     */
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}