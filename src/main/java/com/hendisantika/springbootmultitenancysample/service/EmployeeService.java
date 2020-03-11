package com.hendisantika.springbootmultitenancysample.service;

import com.hendisantika.springbootmultitenancysample.repository.EmployeeRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

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
}
