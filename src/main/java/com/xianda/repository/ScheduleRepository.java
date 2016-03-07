package com.xianda.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xianda.domain.Schedule;
import com.xianda.web.json.bean.ScheduleJsonBean;


@Repository
public interface ScheduleRepository extends PagingAndSortingRepository<Schedule, Long> {
	
	Schedule findById(long id);

	@Query("select count(*) from Schedule e where e.state=0")
	long countActive();

	@Query("select e from Schedule e where e.state=0")
	Page<Schedule> findAllActive(Pageable pageable);

	@Query("select e from Schedule e where e.date LIKE :name")
	Page<Schedule> searchByName(@Param("name")String name, Pageable pageable);
}