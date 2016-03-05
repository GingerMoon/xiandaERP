package com.xianda.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xianda.domain.Customer;
import com.xianda.exception.BusinessException;
import com.xianda.repository.CustomerRepository;
import com.xianda.web.json.bean.CustomerJsonBean;


@Service
public class CustomerService {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	CustomerRepository customerRepository;

	public long count() {
		return this.customerRepository.count();
	}

	public List<CustomerJsonBean> findAll(int tbPageIndex, int tbPageSize) {
		List<Customer> customers = this.customerRepository.findAll(new PageRequest(tbPageIndex, tbPageSize)).getContent();
		List<CustomerJsonBean> results = new ArrayList<CustomerJsonBean>();
		for(int i = 0; i < customers.size(); i++) {
			CustomerJsonBean element = new CustomerJsonBean(customers.get(i));
			results.add(element);
		}
		return results;
	}

	public void add(CustomerJsonBean jsnCustomerBean) {
		Customer entity = jsnCustomerBean.customer();
		this.customerRepository.save(entity);
	}

	public void update(CustomerJsonBean customerBean) {
		Customer entity = customerBean.customer();
		this.customerRepository.save(entity);
		
	}

	public void delete(Long id) {
		this.customerRepository.delete(id);
	}

	public List<CustomerJsonBean> exportExcel() throws BusinessException {
		List<CustomerJsonBean> results = new ArrayList<CustomerJsonBean>();
		try {
			Iterable<Customer> rows = this.customerRepository.findAll();
			for (Customer r : rows) {
				CustomerJsonBean element = new CustomerJsonBean(r);
				results.add(element);
			}
		} catch (Exception e) {
			LOG.error("Exception thrown while listing customers" + e.getMessage());
			throw new BusinessException("Exception thrown while listing cars"  + e.getMessage());
		}
		return results;
	}

	public List<CustomerJsonBean> searchByName(String name) {
		List<Customer> customers = this.customerRepository.searchByName("%"+name+"%", new PageRequest(0, 20)).getContent();
		List<CustomerJsonBean> results = new ArrayList<CustomerJsonBean>();
		for(int i = 0; i < customers.size(); i++) {
			CustomerJsonBean element = new CustomerJsonBean(customers.get(i));
			results.add(element);
		}
		return results;
	}

	public List<CustomerJsonBean> findById(long id) {
		Customer customer = this.customerRepository.findById(id);
		List<CustomerJsonBean> results = new ArrayList<CustomerJsonBean>();
		CustomerJsonBean e = new CustomerJsonBean(customer);
		results.add(e);
		return results;
	}
}
