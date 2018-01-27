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

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.inventory.entity.Good;
import com.thinkgem.jeesite.modules.inventory.service.GoodService;

/**
 * 货物Controller
 * 
 * @author daiyuxiang
 * @version 2017-08-25
 */
@Controller
@RequestMapping(value = "${adminPath}/inventory/good")
public class GoodController extends BaseController {

	@Autowired
	private GoodService goodService;

	@ModelAttribute
	public Good get() {
		Good entity = null;

		if (entity == null) {
			entity = new Good();
		}
		return entity;
	}

	@RequestMapping(value = { "list", "" })
	public String list(Good good, HttpServletRequest request, HttpServletResponse response, Model model) {

		Page<Good> page = new Page<Good>(request, response);

		page = goodService.findPage(page, good);

		model.addAttribute("page", page);

		return "modules/inventory/goodList";
	}
}