package com.xianda.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xianda.domain.Truck;
import com.xianda.exception.BusinessException;
import com.xianda.repository.TruckRepository;
import com.xianda.web.json.bean.TruckJsonBean;


@Service
public class TruckService {

	private static final Logger LOG = LoggerFactory.getLogger(TruckService.class);

	@Autowired
	TruckRepository truckRepository;

	public long count() {
		return this.truckRepository.count();
	}

	public List<TruckJsonBean> findAll(int tbPageIndex, int tbPageSize) {
		List<Truck> trucks = this.truckRepository.findAll(new PageRequest(tbPageIndex, tbPageSize)).getContent();
		List<TruckJsonBean> results = new ArrayList<TruckJsonBean>();
		for(int i = 0; i < trucks.size(); i++) {
			TruckJsonBean element = new TruckJsonBean(trucks.get(i));
			results.add(element);
		}
		return results;
	}

	public void add(TruckJsonBean jsnTruckBean) throws ParseException {
		Truck entity = jsnTruckBean.truck();
		this.truckRepository.save(entity);
	}

	public void update(TruckJsonBean truckBean) throws ParseException {
		Truck entity = truckBean.truck();
		this.truckRepository.save(entity);
		
	}

	public void delete(Long id) {
		this.truckRepository.delete(id);
	}

	public List<TruckJsonBean> exportExcel() throws BusinessException {
		List<TruckJsonBean> results = new ArrayList<TruckJsonBean>();
		try {
			Iterable<Truck> rows = this.truckRepository.findAll();
			for (Truck r : rows) {
				TruckJsonBean element = new TruckJsonBean(r);
				results.add(element);
			}
		} catch (Exception e) {
			LOG.error("Exception thrown while listing trucks" + e.getMessage());
			throw new BusinessException("Exception thrown while listing cars"  + e.getMessage());
		}
		return results;
	}

	public List<TruckJsonBean> searchByName(String name) {
		List<Truck> trucks = this.truckRepository.searchByName("%"+name+"%", new PageRequest(0, 20)).getContent();
		List<TruckJsonBean> results = new ArrayList<TruckJsonBean>();
		for(int i = 0; i < trucks.size(); i++) {
			TruckJsonBean element = new TruckJsonBean(trucks.get(i));
			results.add(element);
		}
		return results;
	}

	public List<TruckJsonBean> findById(long id) {
		Truck truck = this.truckRepository.findById(id);
		List<TruckJsonBean> results = new ArrayList<TruckJsonBean>();
		TruckJsonBean e = new TruckJsonBean(truck);
		results.add(e);
		return results;
	}
}
