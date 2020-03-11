package com.hendisantika.springbootmultitenancysample.repository;

import com.hendisantika.springbootmultitenancysample.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-multi-tenancy-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/03/20
 * Time: 06.26
 */

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
