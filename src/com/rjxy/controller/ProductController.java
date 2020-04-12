package com.rjxy.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.rjxy.model.Category;
import com.rjxy.model.Img;
import com.rjxy.model.Product;
import com.rjxy.service.ICategoryService;
import com.rjxy.service.IImgService;
import com.rjxy.service.IProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	private ICategoryService categoryService;
	private IProductService productService;
	private IImgService imgService;
	
	@Resource
	public void setImgService(IImgService imgService) {
		this.imgService = imgService;
	}
	@Resource
	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}
	@Resource
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	//��ת����Ʒ���ҳ��
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute(new Product());
		//��ѯȫ������Ʒ���
		List<Category> categories = categoryService.list();
		for(Category c:categories) {
			System.out.println(c);
		}
		
		model.addAttribute("categories", categories);
		return "admin/main/product/add";
	}
	
	//����Ʒ������Ӳ���
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(Product product, Integer cid, MultipartFile files[], HttpServletRequest req) {
		Category category = categoryService.load(cid);
		product.setSales(0);//����ӵ���Ʒ������Ϊ0
		product.setStatus(1);//����ӵ���Ʒ״̬Ϊ1���ϼ�״̬��
		productService.add(product);//���������Ʒ
		
		//��ȡͼƬ�ϴ���λ��
		String path = req.getServletContext().getRealPath("/image");
		//�ж��Ƿ�ѡ���ļ�
		if(files != null) {
			for(MultipartFile file:files) {
				//�õ�ͼƬ������
				String fileName = file.getOriginalFilename();
				//����һ���ļ�
				File f = new File(path + "/" + fileName);
				//��ʼ�ϴ�
				try {
					FileUtils.copyInputStreamToFile(file.getInputStream(), f);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//����ͼƬ
				Img img = new Img();
				img.setName(fileName);
				img.setProduct(product);
				//����ͼƬ
				imgService.add(img);
			}
		}
		return "redirect:/product/products";
	}
	
	//��ת����Ʒ��ת�б�ҳ��
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public String list(Model model) {
		List<Product> products = productService.list();
		model.addAttribute("products", products);
		return "admin/main/product/list";
	}
	
	//��Ϊɾ�������漰����Ĺ���������
	//��Ϊ��Ʒ�������� ͼƬ���������������Ҫ������Ʒɾ������ʱҪ��ɾ������Ʒ��ͼƬ()
	//����Ա����Ʒ����ɾ������
	@RequestMapping(value="/{id}/delete", method=RequestMethod.GET)
	public String delete(@PathVariable int id) {
		//��ѯ����Ʒ
		Product product = productService.load(id);
		//������Ʒ��ͼƬɾ��ͼƬ
		imgService.delete(product.getId());
		//ɾ����Ʒ
		productService.delete(id);
		return "redirect:/product/products";
	}
}
