package com.cursosp1.app.dto;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericDTO<T, ID> {
	
	public List<T> findAll();
	
	public Page<T> findAll(Pageable pageable);
	
	public Optional<T> findById(Long ID);
	
	public T save(T entity);
	
	public T update(T entity);
		
	public void deleteById(Long ID);

}
