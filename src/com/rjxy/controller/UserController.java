package com.rjxy.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rjxy.model.Order;
import com.rjxy.model.User;
import com.rjxy.service.ICartService;
import com.rjxy.service.IOrderService;
import com.rjxy.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private IUserService userService;
	private ICartService cartService;
	private IOrderService orderService;
	
	@Resource
	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}

	@Resource
	public void setCartService(ICartService cartService) {
		this.cartService = cartService;
	}

	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	//跳转到用户登录的页面
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "client/login";
	}
	
	//进行用户登录操作
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(String username, String password, HttpSession session) {
		User user = userService.login(username, password);
		System.out.print(user.getUsername());
		session.setAttribute("loginUser",user);
		int cartSize = cartService.loadByUserSize(user.getId());
		System.out.print(cartSize);
		session.setAttribute("cartSize", cartSize);
		//追踪到用户进行登录的页面
		String URI = (String) session.getAttribute("URL");
		if(URI == null || URI.equals("")) {			
			return "redirect:/client/index.html";
		}
		else {
			return "redirect:/"+ URI;
		}
	}
	
	//跳转到用户注册页面
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute(new User());
		return "client/register";
	}
	
	//在注册页面进行操作
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(User user) {
		userService.register(user);
		return "redirect:/user/login";
	}
	
	//进入我的购物网 my_shopping.jsp
	@RequestMapping(value="/my_shopping", method=RequestMethod.GET)
	public String myShopping(HttpSession session, Model model) {
		//获取当前的用户
		User user = (User) session.getAttribute("loginUser");
		if(user == null) {
			return "redirect:/user/login";
		}
		
		return "client/user/my_shopping";
	}
	
	
	
	
	
	
	
	
	
	
	
}
