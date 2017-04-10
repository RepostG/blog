package com.yc.education.model;

import java.util.Date;
import javax.persistence.*;

public class Article {
    /**
     * 文章表
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String contents;

    /**
     * 添加时间
     */
    private Date adddates;

    /**
     * 点击次数
     */
    private Integer clicknum;

    /**
     * 获取文章表
     *
     * @return id - 文章表
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置文章表
     *
     * @param id 文章表
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取内容
     *
     * @return contents - 内容
     */
    public String getContents() {
        return contents;
    }

    /**
     * 设置内容
     *
     * @param contents 内容
     */
    public void setContents(String contents) {
        this.contents = contents;
    }

    /**
     * 获取添加时间
     *
     * @return adddates - 添加时间
     */
    public Date getAdddates() {
        return adddates;
    }

    /**
     * 设置添加时间
     *
     * @param adddates 添加时间
     */
    public void setAdddates(Date adddates) {
        this.adddates = adddates;
    }

    /**
     * 获取点击次数
     *
     * @return clicknum - 点击次数
     */
    public Integer getClicknum() {
        return clicknum;
    }

    /**
     * 设置点击次数
     *
     * @param clicknum 点击次数
     */
    public void setClicknum(Integer clicknum) {
        this.clicknum = clicknum;
    }
}