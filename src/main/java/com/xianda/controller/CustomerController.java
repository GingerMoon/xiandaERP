package com.xianda.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xianda.annotation.Layout;
import com.xianda.service.CustomerService;
import com.xianda.web.json.bean.CustomerJsonBean;
import com.xianda.web.json.response.JsonResponse;
import com.xianda.web.json.response.ListJsonResponse;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Layout(value = "blank")
	@RequestMapping(method = RequestMethod.GET)
	public String show(ModelMap model) {
		return "customer";
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public ListJsonResponse<CustomerJsonBean> get(@RequestParam int tbStartIndex, @RequestParam int tbPageSize) {
		ListJsonResponse<CustomerJsonBean> results;
		List<CustomerJsonBean> lists;
		try {
			long customerCount = customerService.count();
			lists = customerService.findAll(tbStartIndex, tbPageSize);
			results = new ListJsonResponse<CustomerJsonBean>("OK", lists, tbStartIndex, customerService.count());
		} catch (Exception e) {
			results = new ListJsonResponse<CustomerJsonBean>("ERROR", e.getMessage());
		}

		return results;
	}
	
	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	@ResponseBody
	public ListJsonResponse<CustomerJsonBean> get(@RequestParam long id) {
		ListJsonResponse<CustomerJsonBean> results;
		List<CustomerJsonBean> lists;
		try {
			long customerCount = customerService.count();
			lists = customerService.findById(id);
			results = new ListJsonResponse<CustomerJsonBean>("OK", lists, 0, 1);
		} catch (Exception e) {
			results = new ListJsonResponse<CustomerJsonBean>("ERROR", e.getMessage());
		}

		return results;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResponse<CustomerJsonBean> add(@ModelAttribute CustomerJsonBean jsnCustomerBean, BindingResult result) {
		if (result.hasErrors()) {
			return new ListJsonResponse<CustomerJsonBean>("ERROR", "Form invalid");
		}
		try {
			customerService.add(jsnCustomerBean);
		} catch (Exception e) {
			return new ListJsonResponse<CustomerJsonBean>("ERROR", e.getMessage());
		}
		return get((int) (customerService.count()/11), 10);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResponse<CustomerJsonBean> update(@ModelAttribute CustomerJsonBean customerBean, BindingResult result) {
		JsonResponse<CustomerJsonBean> jsonJtableResponse;
		if (result.hasErrors()) {
			return new ListJsonResponse<CustomerJsonBean>("ERROR", "Form invalid");
		}
		try {
			customerService.update(customerBean);
		} catch (Exception e) {
			return new ListJsonResponse<CustomerJsonBean>("ERROR", e.getMessage());
		}
		return get((int) (customerService.count()/11), 10);
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResponse<CustomerJsonBean> remove(@RequestParam String id) {
		try {
			customerService.delete(new Long(id));
		} catch (Exception e) {
			return new ListJsonResponse<CustomerJsonBean>("ERROR", e.getMessage());
		}
		return get((int) (customerService.count()/11), 10);
	}
	
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView exportExcel() {
		List<CustomerJsonBean> customerList = null;
		try {
			customerList = customerService.exportExcel();
		} catch (Exception e) {
			return new ModelAndView("ExcelViewCustomer", "customerList", new ArrayList<CustomerJsonBean>());
		}
		
		return new ModelAndView("ExcelViewCustomer", "customerList", customerList);
	}
	
	@RequestMapping(value = "/searchByName", method = RequestMethod.GET)
	@ResponseBody
	public ListJsonResponse<CustomerJsonBean> searchByName(@RequestParam String name) {
		ListJsonResponse<CustomerJsonBean> results;
		List<CustomerJsonBean> lists;
		try {
			lists = customerService.searchByName(name);
			results = new ListJsonResponse<CustomerJsonBean>("OK", lists);
		} catch (Exception e) {
			results = new ListJsonResponse<CustomerJsonBean>("ERROR", e.getMessage());
		}

		return results;
	}
}
