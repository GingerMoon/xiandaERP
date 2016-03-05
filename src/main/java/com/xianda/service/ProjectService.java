package com.xianda.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xianda.domain.Project;
import com.xianda.exception.BusinessException;
import com.xianda.repository.CustomerRepository;
import com.xianda.repository.ProjectRepository;
import com.xianda.web.json.bean.ProjectJsonBean;


@Service
public class ProjectService {

	private static final Logger LOG = LoggerFactory.getLogger(ProjectService.class);

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	ProjectRepository projectRepository;

	public long count() {
		return this.projectRepository.count();
	}

	public List<ProjectJsonBean> findAll(int tbPageIndex, int tbPageSize) {
		List<Project> projects = this.projectRepository.findAll(new PageRequest(tbPageIndex, tbPageSize)).getContent();
		List<ProjectJsonBean> results = new ArrayList<ProjectJsonBean>();
		for(Project project : projects) {
			project.setCustomer(this.customerRepository.findById(project.getCustomer().getId()));
			ProjectJsonBean element = new ProjectJsonBean(project);
			results.add(element);
		}
		return results;
	}

	public void add(ProjectJsonBean jsnProjectBean) {
		Project entity = jsnProjectBean.project();
		this.projectRepository.save(entity);
	}

	public void update(ProjectJsonBean projectBean) {
		Project entity = projectBean.project();
		this.projectRepository.save(entity);
		
	}

	public void delete(Long id) {
		this.projectRepository.delete(id);
	}

	public List<ProjectJsonBean> exportExcel() throws BusinessException {
		List<ProjectJsonBean> results = new ArrayList<ProjectJsonBean>();
		try {
			Iterable<Project> rows = this.projectRepository.findAll();
			for (Project r : rows) {
				ProjectJsonBean element = new ProjectJsonBean(r);
				results.add(element);
			}
		} catch (Exception e) {
			LOG.error("Exception thrown while listing projects" + e.getMessage());
			throw new BusinessException("Exception thrown while listing cars"  + e.getMessage());
		}
		return results;
	}

	public List<ProjectJsonBean> searchByName(String name) {
		List<Project> projects = this.projectRepository.searchByName("%"+name+"%", new PageRequest(0, 20)).getContent();
		List<ProjectJsonBean> results = new ArrayList<ProjectJsonBean>();
		for(int i = 0; i < projects.size(); i++) {
			ProjectJsonBean element = new ProjectJsonBean(projects.get(i));
			results.add(element);
		}
		return results;
	}

	public List<ProjectJsonBean> findById(long id) {
		Project project = this.projectRepository.findById(id);
		List<ProjectJsonBean> results = new ArrayList<ProjectJsonBean>();
		ProjectJsonBean e = new ProjectJsonBean(project);
		results.add(e);
		return results;
	}
}
