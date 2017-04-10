package com.yc.education.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yc.education.model.Article;
import com.yc.education.service.ArticleService;

@Controller
public class IndexController {

	
	@Autowired
	ArticleService articleService;
	
	/**
	 * 时间轴 -- 文章查询
	 * @return
	 */
	@RequestMapping(value={"","index"})
	public ModelAndView index(){
		ModelAndView result = new ModelAndView("index");
		//文章查询
		List<Article> findall = articleService.findall();
		result.addObject("findall", findall);
		return result;
	}
	
	
	/**
	 * 热度排序
	 */
	@RequestMapping("indexclicknum.html")
	public ModelAndView indexclicknum(){
		ModelAndView result = new ModelAndView("index");
		//时间排序查询
		List<Article> findall = articleService.findclicknum();
		result.addObject("findall", findall);
		return result;
	}
	
	
	
	/**
	 * 文章详情查询
	 * @param id
	 * @return
	 */
	@RequestMapping("about.html")
	public ModelAndView adout(int id){
		ModelAndView result = new ModelAndView();
		//查询详细
		Article findid = articleService.selectByKey(id);
		result.addObject("findid", findid);
		// 点击次数++
		int num = findid.getClicknum() + 1;
		articleService.upNum(num, id);
		return result;
	}
}
