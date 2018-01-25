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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.inventory.entity.Brand;
import com.thinkgem.jeesite.modules.inventory.entity.Order;
import com.thinkgem.jeesite.modules.inventory.entity.Supplier;
import com.thinkgem.jeesite.modules.inventory.service.BrandService;
import com.thinkgem.jeesite.modules.inventory.service.OrderService;
import com.thinkgem.jeesite.modules.inventory.service.SupplierService;
import com.thinkgem.jeesite.modules.inventory.utils.InventoryEnum;
import com.thinkgem.jeesite.modules.inventory.utils.OrderEnum;

/**
 * 订单Controller
 * 
 * @author daiyuxiang
 * @version 2018-01-24
 */
@Controller
@RequestMapping(value = "${adminPath}/inventory/order")
public class OrderController extends BaseController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private BrandService brandService;

	@ModelAttribute
	public Order get(@RequestParam(required = false) String id) {
		Order entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = orderService.get(id);
		}
		if (entity == null) {
			entity = new Order();
		}
		return entity;
	}

	@RequestMapping(value = { "list", "" })
	public String list(Order order, HttpServletRequest request, HttpServletResponse response, Model model) {

		Supplier customerParam = new Supplier();
		customerParam.setTypeArray(
				new String[] { InventoryEnum.SUPPLIER_TYPE_2.getValue(), InventoryEnum.SUPPLIER_TYPE_3.getValue() });
		List<Supplier> customerList = supplierService.findMinList(customerParam);

		Page<Order> page = orderService.findPage(new Page<Order>(request, response), order);

		List<Order> remindList = orderService.queryRemindList(new Order());

		if (!CollectionUtils.isEmpty(remindList)) {
			StringBuffer remindOrderNo = new StringBuffer();

			for (Order remindOrder : remindList) {
				remindOrderNo.append(remindOrder.getOrderNo()).append(",");
			}

			model.addAttribute("remindOrderNo",
					remindOrderNo.toString().substring(0, remindOrderNo.toString().length() - 1));
		}

		model.addAttribute("page", page);
		model.addAttribute("customerList", customerList);

		return "modules/inventory/orderList";
	}

	@RequestMapping(value = "form")
	public String form(Order order, Model model) {
		List<Brand> brandList = brandService.findList(new Brand());

		Supplier supplierParam = new Supplier();
		supplierParam.setTypeArray(
				new String[] { InventoryEnum.SUPPLIER_TYPE_1.getValue(), InventoryEnum.SUPPLIER_TYPE_3.getValue() });
		List<Supplier> supplierList = supplierService.findMinList(supplierParam);

		Supplier customerParam = new Supplier();
		customerParam.setTypeArray(
				new String[] { InventoryEnum.SUPPLIER_TYPE_2.getValue(), InventoryEnum.SUPPLIER_TYPE_3.getValue() });
		List<Supplier> customerList = supplierService.findMinList(customerParam);

		model.addAttribute("order", order);
		model.addAttribute("brandList", brandList);
		model.addAttribute("supplierList", supplierList);
		model.addAttribute("customerList", customerList);

		return "modules/inventory/orderForm";
	}

	@RequestMapping(value = "save")
	public String save(Order order, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, order)) {
			return form(order, model);
		}

		if (order.getIsNewRecord()) {
			order.setOrderStatus(OrderEnum.ORDER_STATUS_0.getValue());
		}

		orderService.save(order);
		addMessage(redirectAttributes, "保存订单成功");
		return "redirect:" + Global.getAdminPath() + "/inventory/order/?repage";
	}

	@RequestMapping(value = "delete")
	public String delete(Order order, RedirectAttributes redirectAttributes) {
		orderService.delete(order);
		addMessage(redirectAttributes, "删除订单成功");
		return "redirect:" + Global.getAdminPath() + "/inventory/order/?repage";
	}

	@RequestMapping(value = "updateStatus")
	public String updateStatus(Order order, RedirectAttributes redirectAttributes) {
		order.setOrderStatus(OrderEnum.ORDER_STATUS_1.getValue());
		orderService.save(order);
		addMessage(redirectAttributes, "处理订单成功");
		return "redirect:" + Global.getAdminPath() + "/inventory/order/?repage";
	}
}