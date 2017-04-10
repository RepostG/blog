package com.yc.education.mapper;

import java.util.List;

import com.yc.education.model.Article;
import com.yc.education.util.MyMapper;

import tk.mybatis.mapper.common.Mapper;

public interface ArticleMapper extends MyMapper<Article> {
	
	//查询全部
	public List<Article> findall();
	
	//修改点击次数
	public int upNum(int id,int num);
	
	//热度排序
	public List<Article> findclicknum();
	
}