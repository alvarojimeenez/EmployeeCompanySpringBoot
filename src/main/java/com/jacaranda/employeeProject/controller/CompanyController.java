package com.jacaranda.employeeProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.employeeProject.model.Company;
import com.jacaranda.employeeProject.service.CompanyService;

@Controller
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@GetMapping("/")
	public String getMenu() {
		return "testLayout";
	}
	
	@GetMapping("/listCompanies")
	public String listCompany(Model model) {
		List<Company> listCompanies = companyService.getCompanies();
		model.addAttribute("listCompanies", listCompanies);
		return "listCompanies";
	}
	
	@GetMapping("/addCompany")
	public String company(Model model) {
		Company company = new Company();
		
		model.addAttribute("company", company);
		return "addCompany";
	}
	
	@GetMapping("/buttonAdd")
	public String addCompany(Model model, @ModelAttribute("company") Company newCompany) {
		Company company = companyService.addCompany(newCompany);
		model.addAttribute("company", company);
		return "addCompany";
	}
	
	
	@GetMapping("/deleteCompany")
	public String deletecompany(Model model, @RequestParam("id") Integer id) {
		Company company = companyService.getCompanyById(id);	
		model.addAttribute("company", company);
		return "deleteCompany";
	}
	
	@GetMapping("/buttonDelete")
	public String buttonDelete(Model model, @ModelAttribute("company") Company delCompany) {
		Company company = companyService.getCompanyById(delCompany.getId());
		String mensaje = "";
		if (company!=null) {
			companyService.deleteCompany(company);
			mensaje = "Borrado";
		}else {
			mensaje = "No existe";
		}
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("company", company);
		return "listCompanies";
	}
	
	@GetMapping("/editCompany")
	public String editCompany(Model model, @RequestParam("id") Integer id) {
		Company company = companyService.getCompanyById(id);	
		model.addAttribute("company", company);
		return "editCompany";
	}
	
	@GetMapping("/buttonEdit")
	public String buttonEdit(Model model, @ModelAttribute("company") Company editCompany) {
		Company company = companyService.addCompany(editCompany);
		model.addAttribute("company", company);
		return "listCompanies";
	}
	
}
