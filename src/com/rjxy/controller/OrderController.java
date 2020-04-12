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
	 * ��������-֧�ֹ��ﳵ�������������
	 * ���������ĳ�ʼ״̬-δ������޸���Ʒ�������Ϳ��
	 * ɾ�����ﳵ��¼
	 */
	@RequestMapping(value="/create_order", method=RequestMethod.POST)
	public String caeateOrder(String base, String name, String phone, String place, 
			String cids, double price, Integer aid, HttpSession session, Model model) {
		//1.��ȡ��ǰ��¼���û�
		User user = (User) session.getAttribute("loginUser");
		//2.�ж����ַ�Ƿ�Ϊ����ӵĵ�ַ
		Address address = null;
		if(base!=null && name!=null && phone!=null && place!=null) {
			//˵���õ�ַΪ����ӵĵ�ַ
			address = new Address();
			address.setBase(base);
			address.setName(name);
			address.setPhone(phone);
			address.setPlace(place);
			address.setStatus(0);//��ʾΪĬ�ϵĵ�ַ
			address.setUser(user);
			addressService.add(address);
		}
		else {
			address = addressService.load(aid);
		}
		//3.��ȡ��ǰʱ��
		Date d = new Date();
		//��ʽ��ʱ��
		String fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
		//ʱ��ת��
		Timestamp date = Timestamp.valueOf(fmt);
		
		//4.����Ϊһ������
		Order order = new Order();
		order.setAddress(address);
		order.setDate(date);
		order.setIsdel(1);//δɾ��״̬
		order.setPrice(price);
		order.setStatus(1);//δ����״̬
		order.setUser(user);
		//���涩��
		orderService.add(order);
		
		//5.������������Ʒ�Ĺ�ϵ
		//���ݹ��ﳵ��cids��¼��ȡ����Ʒ��id
		String cid[] = cids.split(",");
		//��Ϊ��һ������������еĹ��ﳵ��¼�ǿյ�
		for(int i=1; i<cid.length; i++) {
			//���ݹ��ﳵ��id��ѯ���ù��ﳵ�еļ�¼
			Cart cart = cartService.load(Integer.parseInt(cid[i]));
			//��ȡ����Ʒ
			Product product = cart.getProduct();
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setOrder(order);
			orderProduct.setProduct(product);
			orderProduct.setQuantity(cart.getQuantity());
			//����һ����Ʒ�Ͷ����Ĺ�ϵ
			orderProductService.add(orderProduct);
			
			//ͬʱ��Ҫɾ������Ʒ�ڹ��ﳵ�еļ�¼
			cartService.delete(Integer.parseInt(cid[i]));
		}
		//��ȡ���ﳵ�еļ�¼��
		int cartSize = cartService.loadByUserSize(user.getId());
		session.setAttribute("cartSize", cartSize);
		
		//��Ҫ��pay��jspҳ�洫�������
		//��ѯ�ö�������Ϣ
		Order o = orderService.loadAllOne(order.getId());
		model.addAttribute("order", o);
		
		return "client/user/pay";
	}
	

	//��ɶ���֧������
	//����ɹ��󣬱����޸Ŀ�������
	@RequestMapping(value="/payment", method=RequestMethod.POST)
	public String payment(int oid, String password, Model model, HttpSession session) {
		//��ȡ��ǰ��¼���û�
		User user = (User) session.getAttribute("loginUser");
		//���ݶ���id��ѯ�����е���Ʒ
		Order order = orderService.loadAllOne(oid);
		//�ж�����������Ƿ���ȷ
		if(!CodeUtil.getMD5Encoding(password).equals(user.getPassword())) {
			model.addAttribute("errormsg", "*֧�������������������!");
			return "client/user/pay";
		}
		else {
			//��ȡ��������Ʒ�Ĺ�ϵ
			List<OrderProduct> ops = order.getOrderProducts();
			for(OrderProduct op:ops) {
				Product product = op.getProduct();
				//�޸�����
				product.setSales(product.getSales()+op.getQuantity());
				//�޸Ŀ��
				product.setInventory(product.getInventory()-op.getQuantity());
				//�������ݿ��е�����
				productService.updateSalesInventory(product);			
			}
		}
		//�޸��û������
		user.setBalance(user.getBalance()-order.getPrice());
		userService.updateBalance(user);
		
		//�޸Ķ�����״̬Ϊ�Ѹ���
		order.setStatus(2);
		//�������ݿ����Ϣ
		orderService.updateStatus(order);
		model.addAttribute("order", order);
		return "client/user/pay_success";
	}
	
	
	//��ѯ����ͬ״̬�Ķ���
	@RequestMapping(value="/my_order/{item}", method=RequestMethod.GET)
	public String myOrder(@PathVariable int item, HttpSession session, Model model) {
		//��ȡ��ǰ��¼���û�
		User user = (User) session.getAttribute("loginUser");
		//����һ���µĶ���
		Order order = new Order();
		if(item == 0) {
			//��ѯȫ���Ķ���
			List<Order> orders = orderService.listByStatus0(user.getId());
			model.addAttribute("orders", orders);
		}
		else {
			//��ѯ��ͬ״̬�Ķ�����Ӧ�Ķ���1,2,3,4
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
