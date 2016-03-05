package com.xianda.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xianda.domain.Employee;
import com.xianda.web.json.bean.EmployeeJsonBean;


@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
	
	Employee findById(long id);

	@Query("select count(*) from Employee c where c.state=0")
	long countActive();

	@Query("select c from Employee c where c.state=0")
	Page<Employee> findAllActive(Pageable pageable);

	@Query("select c from Employee c where c.name LIKE :name")
	Page<Employee> searchByName(@Param("name")String name, Pageable pageable);
}