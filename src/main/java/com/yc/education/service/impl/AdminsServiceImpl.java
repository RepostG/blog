package com.yc.education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.education.mapper.AdminsMapper;
import com.yc.education.model.Admins;
import com.yc.education.service.AdminsService;

@Service("AdminsServiceImpl")
public class AdminsServiceImpl extends BaseService<Admins> implements AdminsService {

	@Autowired
	AdminsMapper mapper;
	
	@Override
	public Admins findadminlogin(String loginname, String password) {
		// TODO Auto-generated method stub
		try {
			return mapper.findadminlogin(loginname, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		return null;	
		}
	}

}
