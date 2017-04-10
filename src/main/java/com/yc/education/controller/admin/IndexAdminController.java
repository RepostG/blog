package com.yc.education.controller.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.yc.education.model.Article;
import com.yc.education.service.ArticleService;
import com.yc.education.util.AjaxMessage;

@Controller
public class IndexAdminController {

	@Autowired
	ArticleService articleService;
	
	
	/**
	 * 文章查询
	 * @return
	 */
	@RequestMapping("admin/adminarticle.html")
	public ModelAndView adminarticle(@RequestParam(required = false, defaultValue = "1") int page,
			 @RequestParam(required = false, defaultValue = "10") int rows){
		ModelAndView result = new ModelAndView();
		//分页查询
		List<Article> findall = articleService.findall(page, rows);
		result.addObject("pageInfo", new PageInfo<Article>(findall));
		result.addObject("page", page);
		result.addObject("rows", rows);
		return result;
	}
	
	
	/**
	 * ajax -- 删除  -- 文章
	 */
	@ResponseBody
	@RequestMapping("admin/delarticle.html")
	public AjaxMessage<Object> delarticle(int id){
		AjaxMessage<Object> ajax = new AjaxMessage<>();
		int rows = articleService.delete(id);
		return ajax;
	}
	
	/**
	 * 添加  -- 文章  -- 跳转
	 */
	@RequestMapping("admin/addarticle.html")
	public ModelAndView addarticle(){
		ModelAndView result = new ModelAndView();
		
		return result;
	}
	
	
	/**
	 * 添加 -- 文章 -- 处理
	 * @param article
	 * @return
	 */
	@RequestMapping("admin/addarticles.html")
	public ModelAndView addarticles(Article article){
		ModelAndView result = new ModelAndView("redirect:adminarticle.html");
		article.setAdddates(new Date());
		article.setClicknum(0);
		int rows = articleService.save(article);
		return result;
	}
	
	/**
	 * 修改 -- 文章 -- 跳转
	 */
	@RequestMapping("admin/updarticle.html")
	public ModelAndView updarticle(int id){
		ModelAndView result = new ModelAndView();
		Article findid = articleService.selectByKey(id);
		result.addObject("findid", findid);
		return result;
	}
	
	/**
	 * 修改 -- 文章 -- 处理
	 */
	@RequestMapping("admin/updarticles.html")
	public ModelAndView updarticles(Article article){
		ModelAndView result  = new ModelAndView("redirect:adminarticle.html");
		int rows = articleService.updateNotNull(article);
		return result;
	}
}
