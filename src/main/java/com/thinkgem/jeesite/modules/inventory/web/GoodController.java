/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inventory.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.inventory.entity.Brand;
import com.thinkgem.jeesite.modules.inventory.entity.Good;
import com.thinkgem.jeesite.modules.inventory.entity.Supplier;
import com.thinkgem.jeesite.modules.inventory.service.BrandService;
import com.thinkgem.jeesite.modules.inventory.service.GoodService;
import com.thinkgem.jeesite.modules.inventory.service.SupplierService;
import com.thinkgem.jeesite.modules.inventory.utils.InventoryEnum;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

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

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private BrandService brandService;

	@ModelAttribute
	public Good get() {
		Good entity = null;

		if (entity == null) {
			entity = new Good();
			if (!"1".equals(UserUtils.getUser().getUserType())) {
				entity.setCompanyId(UserUtils.getUser().getCompany().getId());
			}
		}
		return entity;
	}

	@RequestMapping(value = "inList")
	public String inList(Good good, HttpServletRequest request, HttpServletResponse response, Model model) {

		Supplier supplierParam = new Supplier();
		supplierParam.setTypeArray(
				new String[] { InventoryEnum.SUPPLIER_TYPE_1.getValue(), InventoryEnum.SUPPLIER_TYPE_3.getValue() });
		List<Supplier> supplierList = supplierService.findMinList(supplierParam);

		Brand brandParam = new Brand();
		List<Brand> brandList = brandService.findList(brandParam);

		good.setType(InventoryEnum.INVENTORY_TYPE_1.getValue());
		good.setGoodsType(InventoryEnum.INVENTORY_TYPE_1.getValue());

		Page<Good> page = goodService.findPage(new Page<Good>(request, response), good);

		model.addAttribute("page", page);
		model.addAttribute("supplierList", supplierList);
		model.addAttribute("brandList", brandList);

		return "modules/inventory/goodInList";
	}

	@RequestMapping(value = "outList")
	public String outList(Good good, HttpServletRequest request, HttpServletResponse response, Model model) {

		Supplier supplierParam = new Supplier();
		supplierParam.setTypeArray(
				new String[] { InventoryEnum.SUPPLIER_TYPE_2.getValue(), InventoryEnum.SUPPLIER_TYPE_3.getValue() });
		List<Supplier> supplierList = supplierService.findMinList(supplierParam);

		Brand brandParam = new Brand();
		List<Brand> brandList = brandService.findList(brandParam);

		good.setType(InventoryEnum.INVENTORY_TYPE_2.getValue());
		good.setGoodsType(InventoryEnum.INVENTORY_TYPE_2.getValue());

		Page<Good> page = goodService.findPage(new Page<Good>(request, response), good);

		model.addAttribute("page", page);
		model.addAttribute("supplierList", supplierList);
		model.addAttribute("brandList", brandList);

		return "modules/inventory/goodOutList";
	}

	@RequestMapping(value = "selectIn")
	public String selectIn(Good good, HttpServletRequest request, HttpServletResponse response, Model model) {

		Supplier supplierParam = new Supplier();
		supplierParam.setTypeArray(
				new String[] { InventoryEnum.SUPPLIER_TYPE_1.getValue(), InventoryEnum.SUPPLIER_TYPE_3.getValue() });
		List<Supplier> supplierList = supplierService.findMinList(supplierParam);

		Brand brandParam = new Brand();
		List<Brand> brandList = brandService.findList(brandParam);

		good.setType(InventoryEnum.INVENTORY_TYPE_1.getValue());
		good.setGoodsType(InventoryEnum.INVENTORY_TYPE_1.getValue());

		Page<Good> page = goodService.findPage(new Page<Good>(request, response), good);

		model.addAttribute("page", page);
		model.addAttribute("supplierList", supplierList);
		model.addAttribute("brandList", brandList);

		return "modules/inventory/selectIn";
	}
}