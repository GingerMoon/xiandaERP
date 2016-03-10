package com.xianda.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xianda.domain.Schedule;
import com.xianda.exception.BusinessException;
import com.xianda.repository.ProjectRepository;
import com.xianda.repository.ScheduleRepository;
import com.xianda.web.json.bean.ScheduleJsonBean;


@Service
public class ScheduleService {

	private static final Logger LOG = LoggerFactory.getLogger(ScheduleService.class);

	@Autowired
	ScheduleRepository scheduleRepository;
	@Autowired
	ProjectRepository projectRepository;

	public long count() {
		return this.scheduleRepository.count();
	}
	
	public long count(Date beginDate, Date endDate) {
		return this.scheduleRepository.count(beginDate, endDate);
	}

	public List<ScheduleJsonBean> findAll(Date beginDate, Date endDate, int tbPageIndex, int tbPageSize) {
		List<Schedule> schedules = this.scheduleRepository.findAll(beginDate, endDate, new PageRequest(tbPageIndex, tbPageSize)).getContent();
		List<ScheduleJsonBean> results = new ArrayList<ScheduleJsonBean>();
		for(int i = 0; i < schedules.size(); i++) {
			ScheduleJsonBean element = new ScheduleJsonBean(schedules.get(i));
			results.add(element);
		}
		return results;
	}

	public void add(ScheduleJsonBean jsnScheduleBean) {
		Schedule entity = jsnScheduleBean.schedule();
		entity.setProject(this.projectRepository.findById(entity.getProject().getId()));
		this.scheduleRepository.save(entity);
	}

	public void update(ScheduleJsonBean scheduleBean) {
		Schedule entity = scheduleBean.schedule();
		entity.setProject(this.projectRepository.findById(entity.getProject().getId()));
		this.scheduleRepository.save(entity);
		
	}

	public void delete(Long id) {
		this.scheduleRepository.delete(id);
	}

	public List<ScheduleJsonBean> exportExcel() throws BusinessException {
		List<ScheduleJsonBean> results = new ArrayList<ScheduleJsonBean>();
		try {
			Iterable<Schedule> rows = this.scheduleRepository.findAll();
			for (Schedule r : rows) {
				ScheduleJsonBean element = new ScheduleJsonBean(r);
				results.add(element);
			}
		} catch (Exception e) {
			LOG.error("Exception thrown while listing schedules" + e.getMessage());
			throw new BusinessException("Exception thrown while listing cars"  + e.getMessage());
		}
		return results;
	}

	public List<ScheduleJsonBean> searchByName(String name) {
		List<Schedule> schedules = this.scheduleRepository.searchByName("%"+name+"%", new PageRequest(0, 20)).getContent();
		List<ScheduleJsonBean> results = new ArrayList<ScheduleJsonBean>();
		for(int i = 0; i < schedules.size(); i++) {
			ScheduleJsonBean element = new ScheduleJsonBean(schedules.get(i));
			results.add(element);
		}
		return results;
	}

	public List<ScheduleJsonBean> findById(long id) {
		Schedule schedule = this.scheduleRepository.findById(id);
		List<ScheduleJsonBean> results = new ArrayList<ScheduleJsonBean>();
		ScheduleJsonBean e = new ScheduleJsonBean(schedule);
		results.add(e);
		return results;
	}
}
