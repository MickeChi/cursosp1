package com.cursosp1.app.dto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cursosp1.app.entity.Employee;
import com.cursosp1.app.entity.WorkBenefit;
import com.cursosp1.app.vo.EmployeeVO;
import com.cursosp1.app.vo.WorkBenefitVO;

public interface EmployeeDTO extends GenericDTO<EmployeeVO, Long>{
	
	/*
	 * public Iterable<Employee> findAll();
	 * 
	 * public Page<Employee> findAll(Pageable pageable);
	 * 
	 * public Optional<Employee> findById(Long id);
	 * 
	 * public Employee save(Employee employee);
	 * 
	 * public Employee update(Employee employee);
	 * 
	 * public void deleteById(Long id);
	 */	
	
	public List<EmployeeVO> findByJobPosition(Long jpId);
	
	public List<EmployeeVO> findByWorkBenefits(List<WorkBenefitVO> workBenVOList);


}
