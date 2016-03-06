package com.xianda.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xianda.domain.Project;


@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
	
	Project findById(long id);

	@Query("select count(*) from Project e where e.state=0")
	long countActive();

	@Query("select e from Project e where e.state=0")
	Page<Project> findAllActive(Pageable pageable);

	@Query("select e from Project e where e.name LIKE :name")
	Page<Project> searchByName(@Param("name")String name, Pageable pageable);

	@Query("select e from Project e where e.address LIKE :address")
	Page<Project> searchByAddress(@Param("address")String address, Pageable pageable);
}