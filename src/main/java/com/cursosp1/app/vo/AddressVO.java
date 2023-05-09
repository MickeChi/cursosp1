package com.cursosp1.app.vo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cursosp1.app.entity.Employee;

import lombok.Data;

@Data
public class AddressVO {

	private Long id;
	
	private Long employee_id;
	
	private String name;
	
	private String street1;
	
	private String street2;
	
	private Integer number;

}
