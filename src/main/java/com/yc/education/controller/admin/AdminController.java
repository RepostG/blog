package com.yc.education.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yc.education.model.Admins;
import com.yc.education.service.AdminsService;
import com.yc.education.util.AjaxMessage;

@Controller
public class AdminController {
//extends BaseController

	@Autowired
	AdminsService adminsService;
	
	//跳转·管理员登录
	@RequestMapping("admin/login.html")
	public ModelAndView adminlogin(){
		return new ModelAndView();
	}
	
	/**
	 * welcome 页面
	 * @return
	 */
	@RequestMapping("admin/welcome.html")
	public ModelAndView welcome(){
		return new ModelAndView();
	}
	
	//登陆成功--跳转
	@RequestMapping("admin/index.html")
	public ModelAndView index(HttpServletResponse response,HttpServletRequest request,HttpSession session){
		ModelAndView result = new ModelAndView();
		//判断管理员时候登录 没有登录定向到登录页面
		Admins loginname = (Admins)request.getSession().getAttribute("adminlogin");
		if(loginname == null){
			try {
				response.sendRedirect("login.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//处理·管理员登录
	@RequestMapping("admin/adminlogins.html")
	public ModelAndView aminlogins(String loginname,String password,Admins admins,HttpSession session){
		//回调信息
		String msg = "* 请输入正确的账号和密码!";
		Admins login  = adminsService.findadminlogin(loginname, password);
		if(login!=null){
			ModelAndView result = new ModelAndView("admin/index");
			session.setAttribute("adminlogin", login);
			return result;
		}else{
			//登录失败--回调信息
			ModelAndView result =new ModelAndView("admin/login");
			result.addObject("msg", msg);
			return result;
		}
	}
	
	/**
	 * 管理员修改密码 
	 */
	@RequestMapping("admin/updadmin.html")
	public ModelAndView updadmin(String adPass){
		ModelAndView result = new ModelAndView();
		
		return result;
	}
	
	/**
	 * ajax 查询管理员密码
	 */
	@ResponseBody
	@RequestMapping("admin/ajaxpass.html")
	public AjaxMessage<Admins> ajaxpass(String adPass){
		AjaxMessage<Admins> ajax = new AjaxMessage<>();
		Admins admins = adminsService.selectByKey(1);
		if(admins.getPassword().equals(adPass)){
			ajax.setIs(true);
		}else{
			ajax.setMsg("* 请输入正确密码!");;
			ajax.setIs(false);
		}
		return ajax;
	}
	
	/**
	 * 修改管理员密码
	 */
	@ResponseBody
	@RequestMapping("admin/updadmins.html")
	public AjaxMessage<Object> updadmins(Admins admins){
	AjaxMessage<Object> ajax = new AjaxMessage<>();
		int rows = adminsService.updateNotNull(admins);
		return ajax;
	}


}
