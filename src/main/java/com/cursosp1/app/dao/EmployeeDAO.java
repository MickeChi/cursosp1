package com.cursosp1.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursosp1.app.entity.Employee;
import com.cursosp1.app.entity.JobPosition;

import java.util.List;
import java.util.Set;
import com.cursosp1.app.entity.WorkBenefit;



@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Long>{
	
	public List<Employee> findByJobPosition(JobPosition jobPosition);
	public List<Employee> findAllByWorkBenefitsIn(Set<WorkBenefit> workBenefits);
	
	
}
