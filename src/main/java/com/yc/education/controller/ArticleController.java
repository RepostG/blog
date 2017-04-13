package com.yc.education.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArticleController {

	
	
	/**
	 * 文章列表页
	 * @return
	 */
	@RequestMapping("list.html")
	public ModelAndView list(){
		ModelAndView result = new ModelAndView();
		
		return result;
	}
	
	
	/**
	 * 文章详情页
	 */
	@RequestMapping("page.html")
	public ModelAndView page(){
		ModelAndView result = new ModelAndView();
		
		return result;
	}
}
