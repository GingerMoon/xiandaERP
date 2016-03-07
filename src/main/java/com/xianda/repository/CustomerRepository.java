package com.xianda.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xianda.domain.Customer;
import com.xianda.web.json.bean.CustomerJsonBean;


@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
	
	Customer findById(long id);

	@Query("select count(*) from Customer e where e.state=0")
	long countActive();

	@Query("select e from Customer e where e.state=0")
	Page<Customer> findAllActive(Pageable pageable);

	@Query("select e from Customer e where e.name LIKE :name")
	Page<Customer> searchByName(@Param("name")String name, Pageable pageable);
}