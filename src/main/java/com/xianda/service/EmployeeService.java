package com.xianda.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xianda.domain.Employee;
import com.xianda.exception.BusinessException;
import com.xianda.repository.EmployeeRepository;
import com.xianda.web.json.bean.EmployeeJsonBean;


@Service
public class EmployeeService {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	EmployeeRepository employeeRepository;

	public long count() {
		return this.employeeRepository.count();
	}

	public List<EmployeeJsonBean> findAll(int tbPageIndex, int tbPageSize) {
		List<Employee> employees = this.employeeRepository.findAll(new PageRequest(tbPageIndex, tbPageSize)).getContent();
		List<EmployeeJsonBean> results = new ArrayList<EmployeeJsonBean>();
		for(int i = 0; i < employees.size(); i++) {
			EmployeeJsonBean element = new EmployeeJsonBean(employees.get(i));
			results.add(element);
		}
		return results;
	}

	public void add(EmployeeJsonBean jsnEmployeeBean) {
		Employee entity = jsnEmployeeBean.employee();
		this.employeeRepository.save(entity);
	}

	public void update(EmployeeJsonBean employeeBean) {
		Employee entity = employeeBean.employee();
		this.employeeRepository.save(entity);
		
	}

	public void delete(Long id) {
		this.employeeRepository.delete(id);
	}

	public List<EmployeeJsonBean> exportExcel() throws BusinessException {
		List<EmployeeJsonBean> results = new ArrayList<EmployeeJsonBean>();
		try {
			Iterable<Employee> rows = this.employeeRepository.findAll();
			for (Employee r : rows) {
				EmployeeJsonBean element = new EmployeeJsonBean(r);
				results.add(element);
			}
		} catch (Exception e) {
			LOG.error("Exception thrown while listing employees" + e.getMessage());
			throw new BusinessException("Exception thrown while listing cars"  + e.getMessage());
		}
		return results;
	}

	public List<EmployeeJsonBean> searchByName(String name) {
		List<Employee> employees = this.employeeRepository.searchByName("%"+name+"%", new PageRequest(0, 20)).getContent();
		List<EmployeeJsonBean> results = new ArrayList<EmployeeJsonBean>();
		for(int i = 0; i < employees.size(); i++) {
			EmployeeJsonBean element = new EmployeeJsonBean(employees.get(i));
			results.add(element);
		}
		return results;
	}

	public List<EmployeeJsonBean> findById(long id) {
		Employee employee = this.employeeRepository.findById(id);
		List<EmployeeJsonBean> results = new ArrayList<EmployeeJsonBean>();
		EmployeeJsonBean e = new EmployeeJsonBean(employee);
		results.add(e);
		return results;
	}
}
