package com.rjxy.controller;

import java.io.IOException;
import java.io.Writer;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rjxy.model.Address;
import com.rjxy.model.Cart;
import com.rjxy.model.Product;
import com.rjxy.model.User;
import com.rjxy.service.IAddressService;
import com.rjxy.service.ICartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	private ICartService cartService;
	private IAddressService addressService;
	
	@Resource
	public void setAddressService(IAddressService addressService) {
		this.addressService = addressService;
	}

	@Resource
	public void setCartService(ICartService cartService) {
		this.cartService = cartService;
	}

	// (前端是用ajax写的)ajax是局部提交，因此 从哪来回哪去，因此没有返回值，若有return 则变成全页面提交
	//将商品加入购物车
	@RequestMapping(value="/addCart", method=RequestMethod.POST)
	public void addCart(int id, int quantity, HttpSession session, HttpServletResponse resp) throws IOException {
		//获取当前登录的用户
		User user = (User) session.getAttribute("loginUser");
		//判断当前用户是否登录
		if(user == null) {
			//此处的URL 必须与UserController 里面的 login方法中的 “追踪到用户进行登录的页面”的url对应
			session.setAttribute("URL", "client/product/" + id + "/item");
			return ;
		}
		else {
			//获取当前时间
			Date d = new Date();
			//格式化时间
			String fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
			//时间转换
			Timestamp date = Timestamp.valueOf(fmt);
			
			Product product = new Product();
			product.setId(id);
			
			Cart cart = new Cart();
			cart.setDate(date);
			cart.setProduct(product);
			cart.setQuantity(quantity);
			cart.setUser(user);
			//保存到数据库
			cartService.add(cart);
			int cartSize = cartService.loadByUserSize(user.getId());
			session.setAttribute("cartSize", cartSize);
			//回到点击 添加到购物车的页面
			Writer out = null;
			try {
				out = resp.getWriter();
				out.write("yes");
			} finally {
				out.close();
			}
		}
	}
	
	//用户查看购物车中的信息
	@RequestMapping(value="/my_cart", method=RequestMethod.GET)
	public String userCart(HttpSession session, Model model) {
		//获取当前的用户
		User user = (User) session.getAttribute("loginUser");
		if(user == null) {
			return "redirect:/user/login";
		}
		else {
			//获取购物车中的信息
			List<Cart> carts = cartService.listByUser(user.getId());
			model.addAttribute("carts", carts);
			return "client/user/my_cart";			
		}
	}
	
	//在购物车界面直接修改其商品数量
	@RequestMapping(value="/updateQuantity", method=RequestMethod.PUT)
	public void updateQuantity(int cid, String option, HttpSession session, HttpServletResponse resp) throws IOException {
		User user = (User) session.getAttribute("loginUser");
		Cart cart = cartService.load(cid);
		if(option.equals("plus")) {
			cart.setQuantity(cart.getQuantity()+1);
		}
		if(option.equals("minus")) {
			cart.setQuantity(cart.getQuantity()-1);
		}
		cartService.updateQuantity(cart);
		
		// 因为是ajax请求
		Writer out = null;
		try {
			out = resp.getWriter();
			out.write("yes");
		} finally {
			out.close();
		}
	}
	
	//删除购物车中的一件商品
	@RequestMapping(value="/delete/{cartid}", method=RequestMethod.GET)
	public String deleteCart(@PathVariable int cartid, HttpSession session, Model model) {
		User user = (User) session.getAttribute("loginUser");
		//删除操作
		cartService.delete(cartid);
		//查询后返回到页面
		List<Cart> carts = cartService.listByUser(user.getId());
		model.addAttribute("carts", carts);
		int cartSize = cartService.loadByUserSize(user.getId());
		model.addAttribute("cartSize", cartSize);
		return "redirect:/cart/my_cart";
	}
	
	//批量删除购物车中的商品
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(int cartids[], HttpSession session, Model model) {
		User user = (User) session.getAttribute("loginUser");
		for(int i=0; i< cartids.length; i++) {
			cartService.delete(cartids[i]);
		}
		List<Cart> carts = cartService.listByUser(user.getId());
		model.addAttribute("carts", carts);
		int cartSize = cartService.loadByUserSize(user.getId());
		model.addAttribute("cartSize", cartSize);
		return "redirect:/cart/my_cart";
	}
	
	//购物车结算操作,跳转到商品结算页面
	@RequestMapping(value="/settlement", method=RequestMethod.POST)
	public String settlement(int cartids[], Model model, HttpSession session) {
		//创建一个购物车，用来保存购物记录
		List<Cart> carts = new ArrayList<>();
		for(int i=0; i<cartids.length; i++){
			Cart cart = cartService.loadByBuy(cartids[i]);
			//保存到该购物车中
			carts.add(cart);
		}
		model.addAttribute("carts", carts);
		//查询该用户所以地址
		User user = (User) session.getAttribute("loginUser");
		List<Address> addresses = addressService.listByUserAdderss(user.getId());
		model.addAttribute("addresses", addresses);
		return "client/user/buy_now";
	}
	
}












