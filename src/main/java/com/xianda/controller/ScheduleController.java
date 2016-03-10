package com.xianda.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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

import com.xianda.CommonTool;
import com.xianda.annotation.Layout;
import com.xianda.service.ScheduleService;
import com.xianda.web.json.bean.ScheduleJsonBean;
import com.xianda.web.json.response.ListJsonResponse;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	@Layout(value = "blank")
	@RequestMapping(method = RequestMethod.GET)
	public String show(ModelMap model) {
		return "schedule";
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public ListJsonResponse<ScheduleJsonBean> get(@RequestParam String beginDate, @RequestParam String endDate, @RequestParam int tbStartIndex, @RequestParam int tbPageSize) {
		ListJsonResponse<ScheduleJsonBean> results;
		List<ScheduleJsonBean> lists;
		Date beginD; Date endD;
		try {
			beginD = CommonTool.dateFormat.parse(beginDate);
			endD = CommonTool.dateFormat.parse(endDate);
		} catch (ParseException e1) {
			beginD = new Date();
			endD = new Date();
			e1.printStackTrace();
		}
		try {
			lists = scheduleService.findAll(beginD, endD, tbStartIndex, tbPageSize);
			results = new ListJsonResponse<ScheduleJsonBean>("OK", lists, tbStartIndex, scheduleService.count(beginD, endD));
		} catch (Exception e) {
			results = new ListJsonResponse<ScheduleJsonBean>("ERROR", e.getMessage());
		}

		return results;
	}
	
	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	@ResponseBody
	public ListJsonResponse<ScheduleJsonBean> get(@RequestParam long id) {
		ListJsonResponse<ScheduleJsonBean> results;
		List<ScheduleJsonBean> lists;
		try {
			lists = scheduleService.findById(id);
			results = new ListJsonResponse<ScheduleJsonBean>("OK", lists, 0, 1);
		} catch (Exception e) {
			results = new ListJsonResponse<ScheduleJsonBean>("ERROR", e.getMessage());
		}

		return results;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResponse<ScheduleJsonBean> add(@ModelAttribute ScheduleJsonBean jsnScheduleBean, BindingResult result) {
		if (result.hasErrors()) {
			return new ListJsonResponse<ScheduleJsonBean>("ERROR", "Form invalid");
		}
		try {
			scheduleService.add(jsnScheduleBean);
		} catch (Exception e) {
			return new ListJsonResponse<ScheduleJsonBean>("ERROR", e.getMessage());
		}
		return get(jsnScheduleBean.getDate(), jsnScheduleBean.getDate(), (int)(scheduleService.count()/11), 10);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResponse<ScheduleJsonBean> update(@ModelAttribute ScheduleJsonBean jsnScheduleBean, BindingResult result) {
		if (result.hasErrors()) {
			return new ListJsonResponse<ScheduleJsonBean>("ERROR", "Form invalid");
		}
		try {
			scheduleService.update(jsnScheduleBean);
		} catch (Exception e) {
			return new ListJsonResponse<ScheduleJsonBean>("ERROR", e.getMessage());
		}
		return get(jsnScheduleBean.getDate(), jsnScheduleBean.getDate(), (int)(scheduleService.count()/11), 10);
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	@ResponseBody
	public ListJsonResponse<ScheduleJsonBean> remove(@RequestParam String date, @RequestParam String id) {
		try {
			scheduleService.delete(new Long(id));
		} catch (Exception e) {
			return new ListJsonResponse<ScheduleJsonBean>("ERROR", e.getMessage());
		}
		return get(date, date, (int)(scheduleService.count()/11), 10);
	}
	
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView exportExcel() {
		List<ScheduleJsonBean> scheduleList = null;
		try {
			scheduleList = scheduleService.exportExcel();
		} catch (Exception e) {
			return new ModelAndView("ExcelViewSchedule", "scheduleList", new ArrayList<ScheduleJsonBean>());
		}
		
		return new ModelAndView("ExcelViewSchedule", "scheduleList", scheduleList);
	}
	
	@RequestMapping(value = "/searchByName", method = RequestMethod.GET)
	@ResponseBody
	public ListJsonResponse<ScheduleJsonBean> searchByName(@RequestParam String name) {
		ListJsonResponse<ScheduleJsonBean> results;
		List<ScheduleJsonBean> lists;
		try {
			lists = scheduleService.searchByName(name);
			results = new ListJsonResponse<ScheduleJsonBean>("OK", lists);
		} catch (Exception e) {
			results = new ListJsonResponse<ScheduleJsonBean>("ERROR", e.getMessage());
		}

		return results;
	}
}
