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
import com.xianda.service.TruckService;
import com.xianda.web.json.bean.TruckJsonBean;
import com.xianda.web.json.response.JsonResponse;
import com.xianda.web.json.response.ListJsonResponse;

@Controller
@RequestMapping("/truck")
public class TruckController {

	@Autowired
	private TruckService truckService;

	@Layout(value = "blank")
	@RequestMapping(method = RequestMethod.GET)
	public String show(ModelMap model) {
		return "truck";
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public ListJsonResponse<TruckJsonBean> get(@RequestParam int tbStartIndex, @RequestParam int tbPageSize) {
		ListJsonResponse<TruckJsonBean> results;
		List<TruckJsonBean> lists;
		try {
			long truckCount = truckService.count();
			lists = truckService.findAll(tbStartIndex, tbPageSize);
			results = new ListJsonResponse<TruckJsonBean>("OK", lists, tbStartIndex, truckService.count());
		} catch (Exception e) {
			results = new ListJsonResponse<TruckJsonBean>("ERROR", e.getMessage());
		}

		return results;
	}
	
	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	@ResponseBody
	public ListJsonResponse<TruckJsonBean> get(@RequestParam long id) {
		ListJsonResponse<TruckJsonBean> results;
		List<TruckJsonBean> lists;
		try {
			long truckCount = truckService.count();
			lists = truckService.findById(id);
			results = new ListJsonResponse<TruckJsonBean>("OK", lists, 0, 1);
		} catch (Exception e) {
			results = new ListJsonResponse<TruckJsonBean>("ERROR", e.getMessage());
		}

		return results;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResponse<TruckJsonBean> add(@ModelAttribute TruckJsonBean jsnTruckBean, BindingResult result) {
		if (result.hasErrors()) {
			return new ListJsonResponse<TruckJsonBean>("ERROR", "Form invalid");
		}
		try {
			truckService.add(jsnTruckBean);
		} catch (Exception e) {
			return new ListJsonResponse<TruckJsonBean>("ERROR", e.getMessage());
		}
		return get((int) (truckService.count()/11), 10);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResponse<TruckJsonBean> update(@ModelAttribute TruckJsonBean truckBean, BindingResult result) {
		JsonResponse<TruckJsonBean> jsonJtableResponse;
		if (result.hasErrors()) {
			return new ListJsonResponse<TruckJsonBean>("ERROR", "Form invalid");
		}
		try {
			truckService.update(truckBean);
		} catch (Exception e) {
			return new ListJsonResponse<TruckJsonBean>("ERROR", e.getMessage());
		}
		return get((int) (truckService.count()/11), 10);
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResponse<TruckJsonBean> remove(@RequestParam String id) {
		try {
			truckService.delete(new Long(id));
		} catch (Exception e) {
			return new ListJsonResponse<TruckJsonBean>("ERROR", e.getMessage());
		}
		return get((int) (truckService.count()/11), 10);
	}
	
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView exportExcel() {
		List<TruckJsonBean> truckList = null;
		try {
			truckList = truckService.exportExcel();
		} catch (Exception e) {
			return new ModelAndView("ExcelViewTruck", "truckList", new ArrayList<TruckJsonBean>());
		}
		
		return new ModelAndView("ExcelViewTruck", "truckList", truckList);
	}
	
	@RequestMapping(value = "/searchByName", method = RequestMethod.GET)
	@ResponseBody
	public ListJsonResponse<TruckJsonBean> searchByName(@RequestParam String name) {
		ListJsonResponse<TruckJsonBean> results;
		List<TruckJsonBean> lists;
		try {
			lists = truckService.searchByName(name);
			results = new ListJsonResponse<TruckJsonBean>("OK", lists);
		} catch (Exception e) {
			results = new ListJsonResponse<TruckJsonBean>("ERROR", e.getMessage());
		}

		return results;
	}
}
