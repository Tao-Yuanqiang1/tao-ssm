package com.rjxy.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rjxy.model.Address;
import com.rjxy.model.Cart;
import com.rjxy.model.Order;
import com.rjxy.model.OrderProduct;
import com.rjxy.model.Product;
import com.rjxy.model.User;
import com.rjxy.service.IAddressService;
import com.rjxy.service.ICartService;
import com.rjxy.service.IOrderProductService;
import com.rjxy.service.IOrderService;
import com.rjxy.service.IProductService;
import com.rjxy.service.IUserService;
import com.rjxy.util.CodeUtil;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	private IAddressService addressService;
	private IOrderService orderService;
	private ICartService cartService;
	private IOrderProductService orderProductService;
	private IProductService productService;
	private IUserService userService;

	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@Resource
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	@Resource
	public void setOrderProductService(IOrderProductService orderProductService) {
		this.orderProductService = orderProductService;
	}

	@Resource
	public void setCartService(ICartService cartService) {
		this.cartService = cartService;
	}

	@Resource
	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}

	@Resource
	public void setAddressService(IAddressService addressService) {
		this.addressService = addressService;
	}

	/**
	 * 创建订单-支持购物车结算和立即购买
	 * 创建订单的初始状态-未付款，不修改商品的销量和库存
	 * 删除购物车记录
	 */
	@RequestMapping(value="/create_order", method=RequestMethod.POST)
	public String caeateOrder(String base, String name, String phone, String place, 
			String cids, double price, Integer aid, HttpSession session, Model model) {
		//1.获取当前登录的用户
		User user = (User) session.getAttribute("loginUser");
		//2.判断其地址是否为新添加的地址
		Address address = null;
		if(base!=null && name!=null && phone!=null && place!=null) {
			//说明该地址为新添加的地址
			address = new Address();
			address.setBase(base);
			address.setName(name);
			address.setPhone(phone);
			address.setPlace(place);
			address.setStatus(0);//表示为默认的地址
			address.setUser(user);
			addressService.add(address);
		}
		else {
			address = addressService.load(aid);
		}
		//3.获取当前时间
		Date d = new Date();
		//格式化时间
		String fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
		//时间转换
		Timestamp date = Timestamp.valueOf(fmt);
		
		//4.保存为一个订单
		Order order = new Order();
		order.setAddress(address);
		order.setDate(date);
		order.setIsdel(1);//未删除状态
		order.setPrice(price);
		order.setStatus(1);//未付款状态
		order.setUser(user);
		//保存订单
		orderService.add(order);
		
		//5.创建订单和商品的关系
		//根据购物车的cids记录获取到商品的id
		String cid[] = cids.split(",");
		//因为第一个存放在数组中的购物车记录是空的
		for(int i=1; i<cid.length; i++) {
			//根据购物车的id查询到该购物车中的记录
			Cart cart = cartService.load(Integer.parseInt(cid[i]));
			//获取到商品
			Product product = cart.getProduct();
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setOrder(order);
			orderProduct.setProduct(product);
			orderProduct.setQuantity(cart.getQuantity());
			//保存一组商品和订单的关系
			orderProductService.add(orderProduct);
			
			//同时需要删除该商品在购物车中的记录
			cartService.delete(Integer.parseInt(cid[i]));
		}
		//获取购物车中的记录数
		int cartSize = cartService.loadByUserSize(user.getId());
		session.setAttribute("cartSize", cartSize);
		
		//需要给pay。jsp页面传输的数据
		//查询该订单的信息
		Order o = orderService.loadAllOne(order.getId());
		model.addAttribute("order", o);
		
		return "client/user/pay";
	}
	

	//完成订单支付功能
	//付款成功后，必须修改库存和销量
	@RequestMapping(value="/payment", method=RequestMethod.POST)
	public String payment(int oid, String password, Model model, HttpSession session) {
		//获取当前登录的用户
		User user = (User) session.getAttribute("loginUser");
		//根据订单id查询订单中的商品
		Order order = orderService.loadAllOne(oid);
		//判断输入的密码是否正确
		if(!CodeUtil.getMD5Encoding(password).equals(user.getPassword())) {
			model.addAttribute("errormsg", "*支付密码错误，请重新输入!");
			return "client/user/pay";
		}
		else {
			//获取订单与商品的关系
			List<OrderProduct> ops = order.getOrderProducts();
			for(OrderProduct op:ops) {
				Product product = op.getProduct();
				//修改销量
				product.setSales(product.getSales()+op.getQuantity());
				//修改库存
				product.setInventory(product.getInventory()-op.getQuantity());
				//更新数据库中的数据
				productService.updateSalesInventory(product);			
			}
		}
		//修改用户的余额
		user.setBalance(user.getBalance()-order.getPrice());
		userService.updateBalance(user);
		
		//修改订单的状态为已付款
		order.setStatus(2);
		//更新数据库的信息
		orderService.updateStatus(order);
		model.addAttribute("order", order);
		return "client/user/pay_success";
	}
	
	
	//查询出不同状态的订单
	@RequestMapping(value="/my_order/{item}", method=RequestMethod.GET)
	public String myOrder(@PathVariable int item, HttpSession session, Model model) {
		//获取当前登录的用户
		User user = (User) session.getAttribute("loginUser");
		//创建一个新的订单
		Order order = new Order();
		if(item == 0) {
			//查询全部的订单
			List<Order> orders = orderService.listByStatus0(user.getId());
			model.addAttribute("orders", orders);
		}
		else {
			//查询不同状态的订单对应的订单1,2,3,4
			order.setStatus(item);
			order.setUser(user);
			List<Order> orders = orderService.listByStatus(order);	
			model.addAttribute("orders", orders);
			if(item == 1) {
				int size = orderService.listOrderBySize(order);
				model.addAttribute("size1", size);				
			}
			if(item == 2) {
				int size = orderService.listOrderBySize(order);
				model.addAttribute("size2", size);				
			}
			if(item == 3) {
				int size = orderService.listOrderBySize(order);
				model.addAttribute("size3", size);				
			}
			if(item == 4) {
				int size = orderService.listOrderBySize(order);
				model.addAttribute("size4", size);				
			}
			if(item == 5) {
				int size = orderService.listOrderBySize(order);
				model.addAttribute("size5", size);				
			}
		}
		return "client/user/my_order";
	}

}
