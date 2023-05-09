package com.cursosp1.app.dto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cursosp1.app.dao.JobPositionDAO;
import com.cursosp1.app.entity.JobPosition;
import com.cursosp1.app.utils.CMapper;
import com.cursosp1.app.vo.EmployeeVO;
import com.cursosp1.app.vo.JobPositionVO;

@Service
public class JobPositionDTOImpl implements JobPositionDTO{
	
	@Autowired
	JobPositionDAO jobPositionDAO;

	@Override
	public List<JobPositionVO> findAll() {
		List<JobPosition> jplist = jobPositionDAO.findAll();
		
		List<JobPositionVO> jpListReturn = jplist.stream().map(j -> CMapper.mapJobPositionToJobPositionVO(j))
				.collect(Collectors.toList());
		
		return jpListReturn;
	
	}

	@Override
	public Page<JobPositionVO> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<JobPositionVO> findById(Long id) {
		
		Optional<JobPosition> jp = jobPositionDAO.findById(id);
	    Optional<JobPositionVO> jpv = Optional.ofNullable(CMapper.mapJobPositionToJobPositionVO(jp.get()));
		return jpv;
	}

	@Override
	public JobPositionVO save(JobPositionVO jpv) {
		// TODO Auto-generated method
		JobPosition jp = CMapper.mapJobPositionVOToJobPosition(jpv, new JobPosition());
		return CMapper.mapJobPositionToJobPositionVO(jobPositionDAO.save(jp));
	}

	@Override
	public JobPositionVO update(JobPositionVO jpv){
		Optional<JobPosition> jpo = jobPositionDAO.findById(jpv.getId());
		JobPosition jp = CMapper.mapJobPositionVOToJobPosition(jpv, jpo.get());
		
		return CMapper.mapJobPositionToJobPositionVO(jobPositionDAO.save(jp));
		
	}

	@Override
	public void deleteById(Long ID) {
		// TODO Auto-generated method stub
		
	}
	

}
