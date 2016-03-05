package com.xianda.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xianda.domain.Truck;
import com.xianda.web.json.bean.TruckJsonBean;


@Repository
public interface TruckRepository extends PagingAndSortingRepository<Truck, Long> {
	
	Truck findById(long id);

	@Query("select count(*) from Truck c where c.state=0")
	long countActive();

	@Query("select c from Truck c where c.state=0")
	Page<Truck> findAllActive(Pageable pageable);

	@Query("select c from Truck c where c.name LIKE :name")
	Page<Truck> searchByName(@Param("name")String name, Pageable pageable);
}