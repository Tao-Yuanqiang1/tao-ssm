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

	// (ǰ������ajaxд��)ajax�Ǿֲ��ύ����� ����������ȥ�����û�з���ֵ������return ����ȫҳ���ύ
	//����Ʒ���빺�ﳵ
	@RequestMapping(value="/addCart", method=RequestMethod.POST)
	public void addCart(int id, int quantity, HttpSession session, HttpServletResponse resp) throws IOException {
		//��ȡ��ǰ��¼���û�
		User user = (User) session.getAttribute("loginUser");
		//�жϵ�ǰ�û��Ƿ��¼
		if(user == null) {
			//�˴���URL ������UserController ����� login�����е� ��׷�ٵ��û����е�¼��ҳ�桱��url��Ӧ
			session.setAttribute("URL", "client/product/" + id + "/item");
			return ;
		}
		else {
			//��ȡ��ǰʱ��
			Date d = new Date();
			//��ʽ��ʱ��
			String fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
			//ʱ��ת��
			Timestamp date = Timestamp.valueOf(fmt);
			
			Product product = new Product();
			product.setId(id);
			
			Cart cart = new Cart();
			cart.setDate(date);
			cart.setProduct(product);
			cart.setQuantity(quantity);
			cart.setUser(user);
			//���浽���ݿ�
			cartService.add(cart);
			int cartSize = cartService.loadByUserSize(user.getId());
			session.setAttribute("cartSize", cartSize);
			//�ص���� ��ӵ����ﳵ��ҳ��
			Writer out = null;
			try {
				out = resp.getWriter();
				out.write("yes");
			} finally {
				out.close();
			}
		}
	}
	
	//�û��鿴���ﳵ�е���Ϣ
	@RequestMapping(value="/my_cart", method=RequestMethod.GET)
	public String userCart(HttpSession session, Model model) {
		//��ȡ��ǰ���û�
		User user = (User) session.getAttribute("loginUser");
		if(user == null) {
			return "redirect:/user/login";
		}
		else {
			//��ȡ���ﳵ�е���Ϣ
			List<Cart> carts = cartService.listByUser(user.getId());
			model.addAttribute("carts", carts);
			return "client/user/my_cart";			
		}
	}
	
	//�ڹ��ﳵ����ֱ���޸�����Ʒ����
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
		
		// ��Ϊ��ajax����
		Writer out = null;
		try {
			out = resp.getWriter();
			out.write("yes");
		} finally {
			out.close();
		}
	}
	
	//ɾ�����ﳵ�е�һ����Ʒ
	@RequestMapping(value="/delete/{cartid}", method=RequestMethod.GET)
	public String deleteCart(@PathVariable int cartid, HttpSession session, Model model) {
		User user = (User) session.getAttribute("loginUser");
		//ɾ������
		cartService.delete(cartid);
		//��ѯ�󷵻ص�ҳ��
		List<Cart> carts = cartService.listByUser(user.getId());
		model.addAttribute("carts", carts);
		int cartSize = cartService.loadByUserSize(user.getId());
		model.addAttribute("cartSize", cartSize);
		return "redirect:/cart/my_cart";
	}
	
	//����ɾ�����ﳵ�е���Ʒ
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
	
	//���ﳵ�������,��ת����Ʒ����ҳ��
	@RequestMapping(value="/settlement", method=RequestMethod.POST)
	public String settlement(int cartids[], Model model, HttpSession session) {
		//����һ�����ﳵ���������湺���¼
		List<Cart> carts = new ArrayList<>();
		for(int i=0; i<cartids.length; i++){
			Cart cart = cartService.loadByBuy(cartids[i]);
			//���浽�ù��ﳵ��
			carts.add(cart);
		}
		model.addAttribute("carts", carts);
		//��ѯ���û����Ե�ַ
		User user = (User) session.getAttribute("loginUser");
		List<Address> addresses = addressService.listByUserAdderss(user.getId());
		model.addAttribute("addresses", addresses);
		return "client/user/buy_now";
	}
	
}












