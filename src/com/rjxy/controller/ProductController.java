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

	//跳转到商品添加页面
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute(new Product());
		//查询全部的商品类别
		List<Category> categories = categoryService.list();
		for(Category c:categories) {
			System.out.println(c);
		}
		
		model.addAttribute("categories", categories);
		return "admin/main/product/add";
	}
	
	//对商品进行添加操作
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(Product product, Integer cid, MultipartFile files[], HttpServletRequest req) {
		Category category = categoryService.load(cid);
		product.setSales(0);//刚添加的商品销售量为0
		product.setStatus(1);//刚添加的商品状态为1（上架状态）
		productService.add(product);//保存添加商品
		
		//获取图片上传的位置
		String path = req.getServletContext().getRealPath("/image");
		//判断是否选择文件
		if(files != null) {
			for(MultipartFile file:files) {
				//得到图片的名字
				String fileName = file.getOriginalFilename();
				//创建一个文件
				File f = new File(path + "/" + fileName);
				//开始上传
				try {
					FileUtils.copyInputStreamToFile(file.getInputStream(), f);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//创建图片
				Img img = new Img();
				img.setName(fileName);
				img.setProduct(product);
				//保存图片
				imgService.add(img);
			}
		}
		return "redirect:/product/products";
	}
	
	//跳转到商品跳转列表页面
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public String list(Model model) {
		List<Product> products = productService.list();
		model.addAttribute("products", products);
		return "admin/main/product/list";
	}
	
	//因为删除操作涉及到表的关联性问题
	//因为商品的主键在 图片表中是外键，所以要进行商品删除操作时要先删除该商品的图片()
	//管理员对商品进行删除操作
	@RequestMapping(value="/{id}/delete", method=RequestMethod.GET)
	public String delete(@PathVariable int id) {
		//查询该商品
		Product product = productService.load(id);
		//根据商品的图片删除图片
		imgService.delete(product.getId());
		//删除商品
		productService.delete(id);
		return "redirect:/product/products";
	}
}
