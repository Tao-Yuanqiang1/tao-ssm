package com.rjxy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rjxy.model.Product;
import com.rjxy.service.IProductService;

@Controller
@RequestMapping("/client")
public class ClientController {

	private IProductService productService;
	
	@Resource
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	//��ת��ǰ̨indexҳ��
	@RequestMapping(value="/index.html", method=RequestMethod.GET)
	public String index(Model model) {
		List<Product> products = productService.listAllProducts();
		for(Product p:products) {
			System.out.println(p.getName());
		}
		model.addAttribute("products", products);
		return "client/index";
	}
	
	//���ͼƬ�鿴����Ʒ����ϸ��Ϣ
	@RequestMapping(value="/product/{id}/item", method=RequestMethod.GET)
	public String message(@PathVariable int id, Model model) {
		Product product = productService.listProduct(id);
		model.addAttribute("product", product);
		return "client/product_item";
	}
}
