/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inventory.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.inventory.entity.Brand;
import com.thinkgem.jeesite.modules.inventory.service.BrandService;

/**
 * 品牌Controller
 * @author daiyuxiang
 * @version 2017-08-21
 */
@Controller
@RequestMapping(value = "${adminPath}/inventory/brand")
public class BrandController extends BaseController {

	@Autowired
	private BrandService brandService;
	
	@ModelAttribute
	public Brand get(@RequestParam(required=false) String id) {
		Brand entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = brandService.get(id);
		}
		if (entity == null){
			entity = new Brand();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Brand brand, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Brand> page = brandService.findPage(new Page<Brand>(request, response), brand); 
		model.addAttribute("page", page);
		return "modules/inventory/brandList";
	}

	@RequestMapping(value = "form")
	public String form(Brand brand, Model model) {
		model.addAttribute("brand", brand);
		return "modules/inventory/brandForm";
	}

	@RequestMapping(value = "save")
	public String save(Brand brand, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, brand)){
			return form(brand, model);
		}
		brandService.save(brand);
		addMessage(redirectAttributes, "保存品牌成功");
		return "redirect:"+Global.getAdminPath()+"/inventory/brand/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(Brand brand, RedirectAttributes redirectAttributes) {
		brandService.delete(brand);
		addMessage(redirectAttributes, "删除品牌成功");
		return "redirect:"+Global.getAdminPath()+"/inventory/brand/?repage";
	}

}