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
import com.xianda.service.EmployeeService;
import com.xianda.web.json.bean.EmployeeJsonBean;
import com.xianda.web.json.response.JsonResponse;
import com.xianda.web.json.response.ListJsonResponse;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Layout(value = "blank")
	@RequestMapping(method = RequestMethod.GET)
	public String show(ModelMap model) {
		return "employee";
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public ListJsonResponse<EmployeeJsonBean> get(@RequestParam int tbStartIndex, @RequestParam int tbPageSize) {
		ListJsonResponse<EmployeeJsonBean> results;
		List<EmployeeJsonBean> lists;
		try {
			long employeeCount = employeeService.count();
			lists = employeeService.findAll(tbStartIndex, tbPageSize);
			results = new ListJsonResponse<EmployeeJsonBean>("OK", lists, tbStartIndex, employeeService.count());
		} catch (Exception e) {
			results = new ListJsonResponse<EmployeeJsonBean>("ERROR", e.getMessage());
		}

		return results;
	}
	
	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	@ResponseBody
	public ListJsonResponse<EmployeeJsonBean> get(@RequestParam long id) {
		ListJsonResponse<EmployeeJsonBean> results;
		List<EmployeeJsonBean> lists;
		try {
			long employeeCount = employeeService.count();
			lists = employeeService.findById(id);
			results = new ListJsonResponse<EmployeeJsonBean>("OK", lists, 0, 1);
		} catch (Exception e) {
			results = new ListJsonResponse<EmployeeJsonBean>("ERROR", e.getMessage());
		}

		return results;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResponse<EmployeeJsonBean> add(@ModelAttribute EmployeeJsonBean jsnEmployeeBean, BindingResult result) {
		if (result.hasErrors()) {
			return new ListJsonResponse<EmployeeJsonBean>("ERROR", "Form invalid");
		}
		try {
			employeeService.add(jsnEmployeeBean);
		} catch (Exception e) {
			return new ListJsonResponse<EmployeeJsonBean>("ERROR", e.getMessage());
		}
		return get((int) (employeeService.count()/11), 10);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResponse<EmployeeJsonBean> update(@ModelAttribute EmployeeJsonBean employeeBean, BindingResult result) {
		JsonResponse<EmployeeJsonBean> jsonJtableResponse;
		if (result.hasErrors()) {
			return new ListJsonResponse<EmployeeJsonBean>("ERROR", "Form invalid");
		}
		try {
			employeeService.update(employeeBean);
		} catch (Exception e) {
			return new ListJsonResponse<EmployeeJsonBean>("ERROR", e.getMessage());
		}
		return get((int) (employeeService.count()/11), 10);
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResponse<EmployeeJsonBean> remove(@RequestParam String id) {
		try {
			employeeService.delete(new Long(id));
		} catch (Exception e) {
			return new ListJsonResponse<EmployeeJsonBean>("ERROR", e.getMessage());
		}
		return get((int) (employeeService.count()/11), 10);
	}
	
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView exportExcel() {
		List<EmployeeJsonBean> employeeList = null;
		try {
			employeeList = employeeService.exportExcel();
		} catch (Exception e) {
			return new ModelAndView("ExcelViewEmployee", "employeeList", new ArrayList<EmployeeJsonBean>());
		}
		
		return new ModelAndView("ExcelViewEmployee", "employeeList", employeeList);
	}
	
	@RequestMapping(value = "/searchByName", method = RequestMethod.GET)
	@ResponseBody
	public ListJsonResponse<EmployeeJsonBean> searchByName(@RequestParam String name) {
		ListJsonResponse<EmployeeJsonBean> results;
		List<EmployeeJsonBean> lists;
		try {
			lists = employeeService.searchByName(name);
			results = new ListJsonResponse<EmployeeJsonBean>("OK", lists);
		} catch (Exception e) {
			results = new ListJsonResponse<EmployeeJsonBean>("ERROR", e.getMessage());
		}

		return results;
	}
}
