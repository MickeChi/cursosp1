package com.cursosp1.app.controller;

import java.util.ArrayList;
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

import com.cursosp1.app.dto.JobPositionDTO;
import com.cursosp1.app.vo.JobPositionVO;

@RestController
@RequestMapping("api/job-position")
public class JobPositionController {
	

	@Autowired
	JobPositionDTO jobPositionDTO;
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		
		List<JobPositionVO> jpList = jobPositionDTO.findAll();
		
		return ResponseEntity.ok(jpList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		
		Optional<JobPositionVO> jpo = jobPositionDTO.findById(id);
		if(!jpo.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(jpo.get());
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody JobPositionVO jobPositionVO){
		
		JobPositionVO jpCreate = jobPositionDTO.save(jobPositionVO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(jpCreate);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody JobPositionVO jobPositionVO, @PathVariable("id") Long id){
		
		try {
			Optional<JobPositionVO> jpo = jobPositionDTO.findById(id);
			if(!jpo.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			JobPositionVO jpUp = jpo.get();
			jpUp.setDescription(jobPositionVO.getDescription());
			jpUp.setName(jobPositionVO.getName());
			
			return ResponseEntity.status(HttpStatus.CREATED).body(jobPositionDTO.update(jpUp));
			
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	

}
