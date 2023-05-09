package com.cursosp1.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
//@Table(schema = "cursosp1", name = "addresses")
@Table(name = "addresses") // for h2 bd
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "employee_id", referencedColumnName = "id")
	private Employee employee;
	
	private String name;
	
	private String street1;
	
	private String street2;
	
	private Integer number;
	
	
	
}
