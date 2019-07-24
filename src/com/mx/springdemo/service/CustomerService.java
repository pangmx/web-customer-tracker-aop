package com.mx.springdemo.service;

import java.util.List;

import com.mx.springdemo.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(int customerId);

	public void deleteCustomer(int customerId);

	public List<Customer> searchCustomers(String searchName);
}
