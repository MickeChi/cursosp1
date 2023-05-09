package com.cursosp1.app.entity;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
//@Table(schema = "cursosp1", name="employees")
@Table(name = "employees") // for h2 bd
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fullName;
	
	private String email;
	
	private Integer phone;
	
	private Boolean isAdmin;
	
	private Date hiringDate;
	
	@OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
	private List<Address> addresses;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "job_position_id", referencedColumnName = "id")
	private JobPosition jobPosition;
	
	@ManyToMany
	@JoinTable(
			  name = "empl_work_benefits", 
			  joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"), 
			  inverseJoinColumns = @JoinColumn(name = "work_benefit_id", referencedColumnName = "id"))
    Set<WorkBenefit> workBenefits;

	
}
