package com.cursosp1.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursosp1.app.dto.EmployeeDTO;
import com.cursosp1.app.entity.Employee;
import com.cursosp1.app.utils.CMapper;
import com.cursosp1.app.vo.EmployeeVO;
import com.cursosp1.app.vo.WorkBenefitVO;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeDTO employeeDTO;
	
	@GetMapping
	public ResponseEntity<?> all(){
		List<EmployeeVO> respEmp = employeeDTO.findAll();
		respEmp.stream().forEach(e -> System.out.println("Emp: " + e.getFullName()));
		return ResponseEntity.ok(respEmp);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody EmployeeVO employee){
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeDTO.save(employee));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id){
		Optional<EmployeeVO> employee = employeeDTO.findById(id);
		if(!employee.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(employee);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody EmployeeVO employeeVO, @PathVariable("id") Long id){
		try {
			Optional<EmployeeVO> empVO = employeeDTO.findById(id);
			if(!empVO.isPresent()) {
				return ResponseEntity.notFound().build();
			}		
			EmployeeVO empUpdate = CMapper.mapEmployeeVOToEmployeeVO(employeeVO, empVO.get());	
						
			return ResponseEntity.status(HttpStatus.CREATED).body(employeeDTO.update(empUpdate));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@GetMapping("/findby-job-postion/{id}")
	public ResponseEntity<?> findByJobPosition(@PathVariable("id") Long id){
		List<EmployeeVO> employees = employeeDTO.findByJobPosition(id);
		return ResponseEntity.ok(employees);
		
	}
	
	@PostMapping("/findby-work-benefits")
	public ResponseEntity<?> findByWorkBenefits(@RequestBody List<WorkBenefitVO> wbVOList){
		
		List<EmployeeVO> employees = employeeDTO.findByWorkBenefits(wbVOList);
		return ResponseEntity.ok(employees);
		
	}

}
