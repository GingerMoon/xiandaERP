package com.xianda.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xianda.annotation.Layout;
import com.xianda.service.RouteService;
import com.xianda.web.json.bean.ProjectJsonBean;
import com.xianda.web.json.bean.RouteJsonBean;
import com.xianda.web.json.response.ListJsonResponse;

@Controller
@RequestMapping("/route")
public class RouteController {

	@Autowired
	private RouteService routeService;

	@Layout(value = "blank")
	@RequestMapping(method = RequestMethod.GET)
	public String show(ModelMap model) {
		return "route";
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public ListJsonResponse<RouteJsonBean> get(@RequestParam int tbStartIndex, @RequestParam int tbPageSize) {
		ListJsonResponse<RouteJsonBean> results;
		List<RouteJsonBean> lists;
		try {
			lists = routeService.findAll(tbStartIndex, tbPageSize);
			results = new ListJsonResponse<RouteJsonBean>(lists, tbStartIndex);
		} catch (Exception e) {
			results = new ListJsonResponse<RouteJsonBean>("ERROR", e.getMessage());
		}

		return results;
	}
	
	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	@ResponseBody
	public ListJsonResponse<RouteJsonBean> get(@RequestParam long id) {
		ListJsonResponse<RouteJsonBean> results;
		List<RouteJsonBean> lists;
		try {
			lists = routeService.findById(id);
			results = new ListJsonResponse<RouteJsonBean>(lists, 0);
		} catch (Exception e) {
			results = new ListJsonResponse<RouteJsonBean>("ERROR", e.getMessage());
		}

		return results;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResponse<RouteJsonBean> add(@ModelAttribute RouteJsonBean jsnRouteBean, BindingResult result) {
		if (result.hasErrors()) {
			return new ListJsonResponse<RouteJsonBean>("ERROR", "Form invalid");
		}
		try {
			routeService.add(jsnRouteBean);
		} catch (Exception e) {
			return new ListJsonResponse<RouteJsonBean>("ERROR", e.getMessage());
		}
		return new ListJsonResponse<RouteJsonBean>();
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResponse<RouteJsonBean> update(@ModelAttribute RouteJsonBean routeBean, BindingResult result) {
		if (result.hasErrors()) {
			return new ListJsonResponse<RouteJsonBean>("ERROR", "Form invalid");
		}
		try {
			routeService.update(routeBean);
		} catch (Exception e) {
			return new ListJsonResponse<RouteJsonBean>("ERROR", e.getMessage());
		}
		return new ListJsonResponse<RouteJsonBean>();
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResponse<RouteJsonBean> remove(@RequestParam String id) {
		try {
			routeService.delete(new Long(id));
		} catch (Exception e) {
			return new ListJsonResponse<RouteJsonBean>("ERROR", e.getMessage());
		}
		return new ListJsonResponse<RouteJsonBean>();
	}
	
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView exportExcel() {
		List<RouteJsonBean> routeList = null;
		try {
			routeList = routeService.exportExcel();
		} catch (Exception e) {
			return new ModelAndView("ExcelViewRoute", "routeList", new ArrayList<RouteJsonBean>());
		}
		
		return new ModelAndView("ExcelViewRoute", "routeList", routeList);
	}
	
	@RequestMapping(value = "/searchProjectAddress", method = RequestMethod.GET)
	@ResponseBody
	public ListJsonResponse<ProjectJsonBean> searchProjectAddress(@RequestParam String projectName) {
		ListJsonResponse<ProjectJsonBean> results;
		List<ProjectJsonBean> lists;
		try {
			lists = routeService.searchProjectAddress(projectName);
			results = new ListJsonResponse<ProjectJsonBean>("OK", lists);
		} catch (Exception e) {
			results = new ListJsonResponse<ProjectJsonBean>("ERROR", e.getMessage());
		}

		return results;
	}
	
	@RequestMapping(value = "/searchByDepartureDestination", method = RequestMethod.GET)
	@ResponseBody
	public ListJsonResponse<RouteJsonBean> searchByDepartureDestination(@RequestParam String departure, @RequestParam String destination) {
		ListJsonResponse<RouteJsonBean> results;
		List<RouteJsonBean> lists;
		try {
			lists = routeService.searchByDepartureDestination(departure, destination);
			results = new ListJsonResponse<RouteJsonBean>("OK", lists);
		} catch (Exception e) {
			results = new ListJsonResponse<RouteJsonBean>("ERROR", e.getMessage());
		}

		return results;
	}
}
