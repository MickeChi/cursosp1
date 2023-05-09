package com.cursosp1.app.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.weaver.NewConstructorTypeMunger;

import com.cursosp1.app.entity.Address;
import com.cursosp1.app.entity.Employee;
import com.cursosp1.app.entity.JobPosition;
import com.cursosp1.app.vo.AddressVO;
import com.cursosp1.app.vo.EmployeeVO;
import com.cursosp1.app.vo.JobPositionVO;
import com.cursosp1.app.vo.WorkBenefitVO;

public class CMapper {
	
	
	public static EmployeeVO mapEmployeeVOToEmployeeVO(EmployeeVO ori, EmployeeVO dest) {
		if(dest.getId() == null) {
			dest.setId(ori.getId());
		}
		
		if(ori.getJobPosition() != null) {
			JobPositionVO job = new JobPositionVO();
			job.setId(ori.getJobPosition().getId());
			job.setDescription(ori.getJobPosition().getDescription());
			job.setName(ori.getJobPosition().getName());
			dest.setJobPosition(job);
		}
		
		dest.setAddresses(ori.getAddresses());
		dest.setWorkBenefits(ori.getWorkBenefits());
		
		dest.setEmail(ori.getEmail());
		dest.setFullName(ori.getFullName());
		dest.setHiringDate(ori.getHiringDate());
		dest.setPhone(ori.getPhone());
		dest.setIsAdmin(ori.getIsAdmin());
		
		return dest;
		
	}
	
	
	public static EmployeeVO mapEmployeeToEmployeeVO(Employee e) {
		EmployeeVO emp = new EmployeeVO();
		if(e.getJobPosition() != null) {
			JobPositionVO job = new JobPositionVO();
			job.setId(e.getJobPosition().getId());
			job.setDescription(e.getJobPosition().getDescription());
			job.setName(e.getJobPosition().getName());
			emp.setJobPosition(job);
		}
		
		if(e.getAddresses() !=null && e.getAddresses().size() > 0) {
			List<AddressVO> adVoLits = e.getAddresses().stream().map(a -> mapAddressToAddressVO(a)).collect(Collectors.toList());
			emp.setAddresses(adVoLits);
		}
		
		if(e.getWorkBenefits() !=null && e.getWorkBenefits().size() > 0) {
			List<WorkBenefitVO> wbList = e.getWorkBenefits().stream().map(a -> {
				return new WorkBenefitVO(a.getId(), a.getName()); 
			}).collect(Collectors.toList());
			emp.setWorkBenefits(wbList);
		}
		
		emp.setId(e.getId());
		emp.setEmail(e.getEmail());
		emp.setFullName(e.getFullName());
		emp.setHiringDate(e.getHiringDate());
		emp.setPhone(e.getPhone());
		emp.setIsAdmin(e.getIsAdmin());
		return emp;
	}
	
	public static Employee mapEmployeeVOToEmployee(EmployeeVO ori, Employee dest) {
		if(dest.getId() == null) {
			dest.setId(ori.getId());
		}
		
		/*
		 * Los obj relacionados se tienen que validar en el dto
		 * if(ori.getJobPosition() != null) { JobPosition job = new JobPosition();
		 * job.setId(ori.getJobPosition().getId());
		 * job.setDescription(ori.getJobPosition().getDescription());
		 * job.setName(ori.getJobPosition().getName()); dest.setJobPosition(job); }
		 */
		
		dest.setEmail(ori.getEmail());
		dest.setFullName(ori.getFullName());
		dest.setHiringDate(ori.getHiringDate());
		dest.setPhone(ori.getPhone());
		dest.setIsAdmin(ori.getIsAdmin());
		
		return dest;
		
	}
	
	public static JobPositionVO mapJobPositionToJobPositionVO(JobPosition jp) {
		JobPositionVO jpv = new JobPositionVO();
		if(jp.getId() != null) {
			jpv.setId(jp.getId());
		}
		jpv.setDescription(jp.getDescription());
		jpv.setName(jp.getName());
		return jpv;
	}
	
	public static JobPosition mapJobPositionVOToJobPosition(JobPositionVO ori, JobPosition dest) {
		if(dest.getId() != null) {
			dest.setId(ori.getId());
		}
		dest.setDescription(ori.getDescription());
		dest.setName(ori.getName());
		return dest;
	}
	
	public static AddressVO mapAddressToAddressVO(Address address) {
		AddressVO adVo = new AddressVO();
		if(address.getId() != null) {
			adVo.setId(address.getId());
		}
		
		//Se comenta ya que se tiene que validar la relacion en el dto
		//EmployeeVO empVO = mapEmployeeToEmployeeVO(address.getEmployee());
		adVo.setEmployee_id(address.getEmployee().getId());
		adVo.setName(address.getName());
		adVo.setNumber(address.getNumber());
		adVo.setStreet1(address.getStreet1());
		adVo.setStreet2(address.getStreet2());
		
		return adVo;
	}
	
	public static Address mapAddressVOToAddress(AddressVO ori, Address dest) {
		if(dest.getId() != null) {
			dest.setId(ori.getId());
		}
		
		/*Se comenta ya que se tiene que validar la relacion en el dto
		 * Employee emp = mapEmployeeVOToEmployee(ori.getEmployee(), new Employee());
		 * dest.setEmployee(emp);
		 */
		dest.setName(ori.getName());
		dest.setNumber(ori.getNumber());
		dest.setStreet1(ori.getStreet1());
		dest.setStreet2(ori.getStreet2());
		
		return dest;
	}


}
