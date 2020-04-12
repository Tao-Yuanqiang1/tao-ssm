package com.rjxy.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rjxy.model.Admin;
import com.rjxy.service.IAdminService;

@Controller
@RequestMapping()
public class AdminController {
	
	private IAdminService adminService;
	
	@Resource
	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	//跳转到登录页面
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "admin/login";
	}
	
	//管理员登录操作
	@RequestMapping(value="/admin/login", method=RequestMethod.POST)
	public String login(String username, String password, HttpSession session) {
		Admin admin = adminService.login(username, password);
		session.setAttribute("loginAdmin", admin);
		return "admin/main/main";
	}
	
	//登录进去后的主页面
	@RequestMapping(value="/admin/top")
	public String top() {
		return "admin/main/top";
	}
	
	@RequestMapping(value="/admin/left")
	public String left() {
		return "admin/main/left";
	}
	
	@RequestMapping(value="/admin/welcome")
	public String welcome() {
		return "admin/main/welcome";
	}
}
