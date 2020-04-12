package com.rjxy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rjxy.model.Category;
import com.rjxy.service.ICategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {

	private ICategoryService categoryService;

	@Resource
	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

	//��̨����Ա��ѯȫ������Ʒ���
	@RequestMapping(value="/categories", method=RequestMethod.GET)
	public String list(Model model) {
		List<Category> categories = categoryService.list();
		model.addAttribute("categories", categories);
		return "admin/main/category/list";
	}
	
	//��ת����Ʒ������ҳ��
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("category", new Category());
		return "admin/main/category/add";
	}
	
	
	//�����Ʒ�������,��ӳɹ�֮��ֱ����ת����ѯȫ������Ʒ�����б�ҳ��
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(Category category) {
		categoryService.add(category);
		return "redirect:/category/categories";
	}
	
	//��ת����Ʒ����޸�ҳ��
	@RequestMapping(value="/{id}/update", method=RequestMethod.GET)
	public String update(@PathVariable int id, Model model) {
		Category category = categoryService.load(id);
		model.addAttribute("category", category);
		return "admin/main/category/update";
	}
	
	//����Ʒ���ҳ��������ݽ����޸�
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public String update(Category category) {
		categoryService.update(category);
		return "redirect:/category/categories";
	}
	
	//����Ʒ������ɾ��
	@RequestMapping(value="/{id}/delete", method=RequestMethod.DELETE)
	public String delete(@PathVariable int id) {
		categoryService.delete(id);
		return "redirect:/category/categories";
	}
	
	
}
