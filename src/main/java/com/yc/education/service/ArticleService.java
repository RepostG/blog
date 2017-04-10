package com.yc.education.service;

import java.util.List;

import com.yc.education.model.Article;

public interface ArticleService extends IService<Article> {

	//查询全部
	List<Article> findall();
	List<Article> findall(int page,int rows);
	
	//热度排序
	List<Article> findclicknum();
	
	//修改点击次数
	int upNum(int id,int num);
}
