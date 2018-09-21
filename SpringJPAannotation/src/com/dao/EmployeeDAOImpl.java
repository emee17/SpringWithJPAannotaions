package com.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pojo.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	@Transactional
	@Override
	public void createEmployee(Employee employee) 
	{
		entityManager.persist(employee);
	}

	@Override
	public Employee getEmployeeById(int employeeid) 
	{
		Employee employee = entityManager.find(Employee.class, employeeid);
		return employee;
	}

	@Transactional
	@Override
	public void deleteEmployeeById(int employeeid)
	{
		Employee employee = entityManager.find(Employee.class, employeeid);
		entityManager.remove(employee);
		System.out.println("SuccessFully Deleted");
	}

	@Transactional
	@Override
	public void updateEmployeeById(String email, int employeeid) 
	{
		Employee employee = entityManager.find(Employee.class, employeeid);
		employee.setEmail(email);
		entityManager.merge(employee);
	}

	@Override
	public List<Employee> getAllEmployeeDetails() 
	{
		Query query = entityManager.createQuery("from springhibernate");
		
		List<Employee> list  = query.getResultList();
		return list;
	}

	
	
}
