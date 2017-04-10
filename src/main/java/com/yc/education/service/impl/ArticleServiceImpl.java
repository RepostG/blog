package com.yc.education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.yc.education.mapper.ArticleMapper;
import com.yc.education.model.Article;
import com.yc.education.service.ArticleService;

@Service("ArticleServiceImpl")
public class ArticleServiceImpl extends BaseService<Article> implements ArticleService {

	@Autowired
	ArticleMapper mapper;
	
	@Override
	public List<Article> findall() {
		// TODO Auto-generated method stub
		try {
			return mapper.findall();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		return null;	
		}
	}

	@Override
	public List<Article> findall(int page, int rows) {
		// TODO Auto-generated method stub
		try {
			PageHelper.startPage(page, rows);
			return mapper.findall();
		} catch (Exception e) {
			// TODO Auto-generated catch bloc
		return null;	
		}
	}

	@Override
	public int upNum(int id, int num) {
		// TODO Auto-generated method stub
		try {
			return mapper.upNum(id, num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		return 0;	
		}
	}

	@Override
	public List<Article> findclicknum() {
		// TODO Auto-generated method stub
		try {
			return mapper.findclicknum();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		return null;	
		}
	}

}
