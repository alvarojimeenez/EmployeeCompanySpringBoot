package com.jacaranda.employeeProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.employeeProject.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
	
	
}
