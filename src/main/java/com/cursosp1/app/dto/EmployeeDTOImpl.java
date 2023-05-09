package com.cursosp1.app.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursosp1.app.dao.AddressDAO;
import com.cursosp1.app.dao.EmployeeDAO;
import com.cursosp1.app.dao.JobPositionDAO;
import com.cursosp1.app.dao.WorkBenefitDAO;
import com.cursosp1.app.entity.Address;
import com.cursosp1.app.entity.Employee;
import com.cursosp1.app.entity.JobPosition;
import com.cursosp1.app.entity.WorkBenefit;
import com.cursosp1.app.utils.CMapper;
import com.cursosp1.app.vo.AddressVO;
import com.cursosp1.app.vo.EmployeeVO;
import com.cursosp1.app.vo.JobPositionVO;
import com.cursosp1.app.vo.WorkBenefitVO;

@Service
public class EmployeeDTOImpl implements EmployeeDTO{
	
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Autowired
	private JobPositionDAO jobPositionDAO;
	
	@Autowired
	private AddressDAO addressDAO;
	
	@Autowired
	private WorkBenefitDAO workBenefitDAO;

	@Override
	@Transactional(readOnly = true)
	public List<EmployeeVO> findAll() {
		List<EmployeeVO> result = new ArrayList<>();
		
		List<Employee> employees = employeeDAO.findAll();
		if(employees.size() > 0) {
			result = employees.stream().map(e-> CMapper.mapEmployeeToEmployeeVO(e)).collect(Collectors.toList());
		}
		
		System.out.println("employees: " + result);
		
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<EmployeeVO> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<EmployeeVO> findById(Long id) {
		Optional<Employee> emp = employeeDAO.findById(id);
		
	    Optional<EmployeeVO> resp = Optional.ofNullable(CMapper.mapEmployeeToEmployeeVO(emp.get()));

		return resp;
	}

	@Override
	@Transactional
	public EmployeeVO save(EmployeeVO employeeVO) {
		
		Employee emp = CMapper.mapEmployeeVOToEmployee(employeeVO, new Employee());
		
		if(employeeVO.getJobPosition() != null) {
			Optional<JobPosition> jp = jobPositionDAO.findById(employeeVO.getJobPosition().getId());
			emp.setJobPosition(jp.get());
		}
		
		if(employeeVO.getWorkBenefits().size() > 0) {
			Set<WorkBenefit> wbList = this.createWorkBenefitList(employeeVO.getWorkBenefits());
			emp.setWorkBenefits(wbList);
		}
		
		Employee empCreated = employeeDAO.save(emp);
		
		if(employeeVO.getAddresses().size() > 0) {
			List<Address> addList = employeeVO.getAddresses().stream()
					.map(a -> this.addAddreessToEmployee(a, empCreated)).collect(Collectors.toList());
			empCreated.setAddresses(addList);
		}
		
		return CMapper.mapEmployeeToEmployeeVO(empCreated);

	}

	@Override
	@Transactional
	public EmployeeVO update(EmployeeVO employeeVO) {
		Optional<Employee> empo = employeeDAO.findById(employeeVO.getId());
		
		Employee emp = CMapper.mapEmployeeVOToEmployee(employeeVO, empo.get());
		if(employeeVO.getJobPosition() != null) {
			Optional<JobPosition> jp = jobPositionDAO.findById(employeeVO.getJobPosition().getId());
			emp.setJobPosition(jp.get());
		}
		
		if(employeeVO.getWorkBenefits().size() > 0) {
			Set<WorkBenefit> wbList = this.createWorkBenefitList(employeeVO.getWorkBenefits());
			emp.setWorkBenefits(wbList);
		}
		
		Employee empUpdated = employeeDAO.save(emp);
		
		if(employeeVO.getAddresses().size() > 0) {
			List<Address> addList = employeeVO.getAddresses().stream()
					.map(a -> this.addAddreessToEmployee(a, empUpdated)).collect(Collectors.toList());
			empUpdated.setAddresses(addList);
		}
		
		return CMapper.mapEmployeeToEmployeeVO(empUpdated);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmployeeVO> findByJobPosition(Long jpId) {
		List<EmployeeVO> result = new ArrayList<>();
		Optional<JobPosition> jpo = jobPositionDAO.findById(jpId);
		
		List<Employee> employees = employeeDAO.findByJobPosition(jpo.get());
				
		if(employees.size() > 0) {
			result = employees.stream().map(e-> CMapper.mapEmployeeToEmployeeVO(e)).collect(Collectors.toList());
		}
				
		return result;
	}
	
	private Address addAddreessToEmployee(AddressVO addr, Employee emp) {
		
		Address addressCreate = CMapper.mapAddressVOToAddress(addr, new Address());
		if(addr.getId() != null) {
			Optional<Address> addOp = addressDAO.findById(addr.getId());
			if(addOp.isPresent()) {
				addressCreate = CMapper.mapAddressVOToAddress(addr, addOp.get());
			}
		}
		
		addressCreate.setEmployee(emp);
		return addressDAO.save(addressCreate);
	}
	
	private Set<WorkBenefit> createWorkBenefitList(List<WorkBenefitVO> wbVOList){
			return wbVOList.stream().map(a -> {
				if(a.getId() != null) {
					Optional<WorkBenefit> wbop = workBenefitDAO.findById(a.getId());
					return wbop.get();
				}
				WorkBenefit wb = new WorkBenefit();
				wb.setName(a.getName());
				return workBenefitDAO.save(wb);
			}).collect(Collectors.toSet());
					
	}

	@Override
	public List<EmployeeVO> findByWorkBenefits(List<WorkBenefitVO> workBenVOList) {
		List<EmployeeVO> result = new ArrayList<>();
		Set<WorkBenefit> wb = this.createWorkBenefitList(workBenVOList);
		List<Employee> employees = employeeDAO.findAllByWorkBenefitsIn(wb);
				
		if(employees.size() > 0) {
			result = employees.stream().map(e-> CMapper.mapEmployeeToEmployeeVO(e)).collect(Collectors.toList());
		}
				
		return result;
	}
	
	
	
	
	
	
	
	
	

	

	

	

	

	

	
	
	

}
