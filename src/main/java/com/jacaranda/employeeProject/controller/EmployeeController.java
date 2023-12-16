package com.jacaranda.employeeProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.employeeProject.model.Company;
import com.jacaranda.employeeProject.model.Employee;
import com.jacaranda.employeeProject.service.CompanyService;
import com.jacaranda.employeeProject.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private CompanyService companyService;
	
	@GetMapping("/listEmployees")
	public String listEmployee(Model model) {
		List<Employee> listEmployees = employeeService.getEmployees();
		model.addAttribute("listEmployees", listEmployees);
		return "listEmployees";
	}
	
	@GetMapping("/addEmployee")
	public String employee(Model model) {
		Employee employee = new Employee();
		List<Company> listCompanies = companyService.getCompanies();
		model.addAttribute("listCompanies", listCompanies);
		model.addAttribute("employee", employee);
		return "addEmployee";
	}
	
	@GetMapping("/buttonAddEmployee")
	public String addCompany(Model model, @ModelAttribute("employee") Employee newEmployee) {
		Employee employee = employeeService.addEmployee(newEmployee);
		model.addAttribute("employee", employee);
		return "addEmployee";
	}
	
	@GetMapping("/editEmployee")
	public String editEmployee(Model model, @RequestParam("id") Integer id) {
		Employee employee = employeeService.getEmployeeById(id);
		List<Company> listCompanies = companyService.getCompanies();
		model.addAttribute("listCompanies", listCompanies);
		model.addAttribute("employee", employee);
		return "editEmployee";
	}
	
	@GetMapping("/buttonEditEmployee")
	public String editEmployee(Model model, @ModelAttribute("employee") Employee editEmployee) {
		Employee employee = employeeService.addEmployee(editEmployee);
		model.addAttribute("employee", employee);
		return "editEmployee";
	}
	
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(Model model, @RequestParam("id") Integer id) {
		Employee employee = employeeService.getEmployeeById(id);	
		model.addAttribute("employee", employee);
		return "deleteEmployee";
	}
	
	@GetMapping("/buttonDeleteEmployee")
	public String buttonDeleteEmployee(Model model, @ModelAttribute("employee") Employee delEmployee) {
		Employee employee = employeeService.getEmployeeById(delEmployee.getId());
		String mensaje = "";
		if (employee!=null) {
			employeeService.deleteEmployee(employee);
			mensaje = "Borrado";
		}else {
			mensaje = "No existe";
		}
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("employee", employee);
		return "deleteEmployee";
	}
	
}
