package com.yc.education.mapper;

import com.yc.education.model.Admins;
import com.yc.education.util.MyMapper;


public interface AdminsMapper extends MyMapper<Admins> {
	
	//admin登录
	public Admins findadminlogin(String loginname,String password);
}