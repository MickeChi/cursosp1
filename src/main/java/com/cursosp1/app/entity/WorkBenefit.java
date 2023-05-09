package com.cursosp1.app.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "work_benefits")
public class WorkBenefit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	//En este ejemplo no necesitamos la relacion inversa, los beneficios no tienen infor del usuario
	/*
	 * @ManyToMany(mappedBy = "workBenefits") Set<Employee> benefitedEmpl;
	 */
}
