package com.cursosp1.app.vo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.cursosp1.app.entity.Address;
import com.cursosp1.app.entity.JobPosition;

import lombok.Data;

@Data
public class EmployeeVO {
	
	private Long id;
	
	private String fullName;
	
	private String email;
	
	private Integer phone;
	
	private Boolean isAdmin;
	
	private Date hiringDate;
	
	private List<AddressVO> addresses;
	
	private List<WorkBenefitVO> workBenefits;
	
	private JobPositionVO jobPosition;

	public EmployeeVO() {
		this.addresses = new ArrayList<>();
		this.workBenefits = new ArrayList<>();
	}
	
	

}
