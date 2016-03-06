package com.xianda.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xianda.domain.Route;
import com.xianda.web.json.bean.RouteJsonBean;


@Repository
public interface RouteRepository extends PagingAndSortingRepository<Route, Long> {
	
	Route findById(long id);

	@Query("select count(*) from Route e where e.state=0")
	long countActive();

	@Query("select e from Route e where e.state=0")
	Page<Route> findAllActive(Pageable pageable);
}