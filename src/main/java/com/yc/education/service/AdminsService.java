package com.yc.education.service;

import com.yc.education.model.Admins;

public interface AdminsService extends IService<Admins> {
	
	
	//admin登录
	Admins findadminlogin(String loginname,String password);
	
	

}
