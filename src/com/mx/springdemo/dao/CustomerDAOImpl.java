package com.mx.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mx.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Customer> getCustomers() {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create query
		Query<Customer> query = currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		//execute query
		List<Customer> customers = query.getResultList();
		
		//return result
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save customer
		currentSession.saveOrUpdate(customer);
		
	}

	@Override
	public Customer getCustomer(int customerId) {
		
		//get current session
		Session session = sessionFactory.getCurrentSession();
		
		//get customer by id
		Customer customer = session.get(Customer.class, customerId);
		
		return customer;
	}

	@Override
	public void deleteCustomer(int customerId) {
		
		//get current session
		Session session = sessionFactory.getCurrentSession();
		
		//create query to delete customer
		Query query = session.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId", customerId);

		query.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String searchName) {
		
		//get current session
		Session session = sessionFactory.getCurrentSession();
				
		//create query to search customers by name
		Query query = session.createQuery("from Customer "
				+ "where lower(firstName) like :searchName "
				+ "or lower(lastName) like :searchName", Customer.class);
		query.setParameter("searchName", "%"+searchName.toLowerCase()+"%");
		
		List<Customer> customerList = query.getResultList();
		
		return customerList;
	}

}
