package com.cursosp1.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursosp1.app.entity.JobPosition;

@Repository
public interface JobPositionDAO extends JpaRepository<JobPosition, Long>{

}
