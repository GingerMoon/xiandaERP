package com.xianda.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xianda.domain.Route;
import com.xianda.exception.BusinessException;
import com.xianda.repository.RouteRepository;
import com.xianda.web.json.bean.ProjectJsonBean;
import com.xianda.web.json.bean.RouteJsonBean;


@Service
public class RouteService {

	private static final Logger LOG = LoggerFactory.getLogger(RouteService.class);

	@Autowired
	RouteRepository routeRepository;

	@Autowired
	ProjectService projectService;

	public long count() {
		return this.routeRepository.count();
	}

	public List<RouteJsonBean> findAll(int tbPageIndex, int tbPageSize) {
		List<Route> routes = this.routeRepository.findAll(new PageRequest(tbPageIndex, tbPageSize)).getContent();
		List<RouteJsonBean> results = new ArrayList<RouteJsonBean>();
		for(int i = 0; i < routes.size(); i++) {
			RouteJsonBean element = new RouteJsonBean(routes.get(i));
			results.add(element);
		}
		return results;
	}

	public void add(RouteJsonBean jsnRouteBean) {
		Route entity = jsnRouteBean.route();
		this.routeRepository.save(entity);
	}

	public void update(RouteJsonBean routeBean) {
		Route entity = routeBean.route();
		this.routeRepository.save(entity);
		
	}

	public void delete(Long id) {
		this.routeRepository.delete(id);
	}

	public List<RouteJsonBean> exportExcel() throws BusinessException {
		List<RouteJsonBean> results = new ArrayList<RouteJsonBean>();
		try {
			Iterable<Route> rows = this.routeRepository.findAll();
			for (Route r : rows) {
				RouteJsonBean element = new RouteJsonBean(r);
				results.add(element);
			}
		} catch (Exception e) {
			LOG.error("Exception thrown while listing routes" + e.getMessage());
			throw new BusinessException("Exception thrown while listing cars"  + e.getMessage());
		}
		return results;
	}

	public List<ProjectJsonBean> searchProjectAddress(String projectName) {
		List<ProjectJsonBean> results = this.projectService.searchByName(projectName);
		return results;
	}

	public List<RouteJsonBean> findById(long id) {
		Route route = this.routeRepository.findById(id);
		List<RouteJsonBean> results = new ArrayList<RouteJsonBean>();
		RouteJsonBean e = new RouteJsonBean(route);
		results.add(e);
		return results;
	}
}
