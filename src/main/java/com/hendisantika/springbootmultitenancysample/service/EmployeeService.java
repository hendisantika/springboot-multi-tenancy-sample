package com.hendisantika.springbootmultitenancysample.service;

import com.hendisantika.springbootmultitenancysample.model.Employee;
import com.hendisantika.springbootmultitenancysample.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-multi-tenancy-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/03/20
 * Time: 06.29
 */
@Service
public class EmployeeService implements ApplicationRunner {

    private final EmployeeRepository employeeRepository;

    @PersistenceContext
    public EntityManager entityManager;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public List<Employee> listEmployees() {
        return employeeRepository.findAll();
    }

    @Transactional
    public Optional<Employee> getEmployee(String employeeId) {
        return employeeRepository.findById(employeeId);
    }

}
