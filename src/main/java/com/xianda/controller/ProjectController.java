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
import com.xianda.service.ProjectService;
import com.xianda.web.json.bean.ProjectJsonBean;
import com.xianda.web.json.response.JsonResponse;
import com.xianda.web.json.response.ListJsonResponse;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Layout(value = "blank")
	@RequestMapping(method = RequestMethod.GET)
	public String show(ModelMap model) {
		return "project";
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public ListJsonResponse<ProjectJsonBean> get(@RequestParam int tbStartIndex, @RequestParam int tbPageSize) {
		ListJsonResponse<ProjectJsonBean> results;
		List<ProjectJsonBean> lists;
		try {
			lists = projectService.findAll(tbStartIndex, tbPageSize);
			results = new ListJsonResponse<ProjectJsonBean>("OK", lists, tbStartIndex, projectService.count());
		} catch (Exception e) {
			results = new ListJsonResponse<ProjectJsonBean>("ERROR", e.getMessage());
		}

		return results;
	}
	
	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	@ResponseBody
	public ListJsonResponse<ProjectJsonBean> get(@RequestParam long id) {
		ListJsonResponse<ProjectJsonBean> results;
		List<ProjectJsonBean> lists;
		try {
			lists = projectService.findById(id);
			results = new ListJsonResponse<ProjectJsonBean>("OK", lists, 0, 1);
		} catch (Exception e) {
			results = new ListJsonResponse<ProjectJsonBean>("ERROR", e.getMessage());
		}

		return results;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResponse<ProjectJsonBean> add(@ModelAttribute ProjectJsonBean jsnProjectBean, BindingResult result) {
		if (result.hasErrors()) {
			return new ListJsonResponse<ProjectJsonBean>("ERROR", "Form invalid");
		}
		try {
			projectService.add(jsnProjectBean);
		} catch (Exception e) {
			return new ListJsonResponse<ProjectJsonBean>("ERROR", e.getMessage());
		}
		return get((int) (projectService.count()/11), 10);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResponse<ProjectJsonBean> update(@ModelAttribute ProjectJsonBean projectBean, BindingResult result) {
		JsonResponse<ProjectJsonBean> jsonJtableResponse;
		if (result.hasErrors()) {
			return new ListJsonResponse<ProjectJsonBean>("ERROR", "Form invalid");
		}
		try {
			projectService.update(projectBean);
		} catch (Exception e) {
			return new ListJsonResponse<ProjectJsonBean>("ERROR", e.getMessage());
		}
		return get((int) (projectService.count()/11), 10);
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResponse<ProjectJsonBean> remove(@RequestParam String id) {
		try {
			projectService.delete(new Long(id));
		} catch (Exception e) {
			return new ListJsonResponse<ProjectJsonBean>("ERROR", e.getMessage());
		}
		return get((int) (projectService.count()/11), 10);
	}
	
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView exportExcel() {
		List<ProjectJsonBean> projectList = null;
		try {
			projectList = projectService.exportExcel();
		} catch (Exception e) {
			return new ModelAndView("ExcelViewProject", "projectList", new ArrayList<ProjectJsonBean>());
		}
		
		return new ModelAndView("ExcelViewProject", "projectList", projectList);
	}
	
	@RequestMapping(value = "/searchByName", method = RequestMethod.GET)
	@ResponseBody
	public ListJsonResponse<ProjectJsonBean> searchByName(@RequestParam String name) {
		ListJsonResponse<ProjectJsonBean> results;
		List<ProjectJsonBean> lists;
		try {
			lists = projectService.searchByName(name);
			results = new ListJsonResponse<ProjectJsonBean>("OK", lists);
		} catch (Exception e) {
			results = new ListJsonResponse<ProjectJsonBean>("ERROR", e.getMessage());
		}

		return results;
	}
}
