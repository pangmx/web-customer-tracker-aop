package com.mx.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mx.springdemo.entity.Customer;
import com.mx.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	//get Spring to inject the customer service
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model model) {
		
		List<Customer> customers = customerService.getCustomers();
		
		model.addAttribute("customers", customers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAddCustomer")
	public String showFormForAddCustomer(Model model) {
		
		Customer customer = new Customer();
		
		model.addAttribute("customer", customer);
		
		return "customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		
		customerService.saveCustomer(customer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int customerId, 
			Model model) {
		
		//get customer by id
		Customer customer = customerService.getCustomer(customerId);
		
		//set customer into model
		model.addAttribute("customer", customer);
		
		return "customer-form";
	}
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int customerId, 
			Model model) {
		
		customerService.deleteCustomer(customerId);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/searchCustomer")
	public String searchCustomer(@RequestParam("theSearchName") String searchName, 
			Model model){
		
		List<Customer> customers = customerService.searchCustomers(searchName);
		
		model.addAttribute("customers", customers);
		
		return "list-customers";
	}
	
}
